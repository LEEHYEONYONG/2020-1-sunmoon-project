package stat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import db.Connect_DB;
import db.PosDto;
import db.PosUse;

public class ViewStatDayService implements ActionListener, ItemListener{


	private Vector<PosUse> results; // 조회여부 판단용 전역설정 (사용은 테이블에만)

	private String statType = "매출합계"; // 라디오버튼 값 (디폴트는 "매출합계")

	private ViewStatDay vd;

	// [생성자]
	public ViewStatDayService(ViewStatDay vd) {
		this.vd = vd;

		setChart(statType);
	}

	// [ActionListener override/조회버튼]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vd.btnSearch) {
			search();
		}
	}

	// [ItemListener override/라디오버튼]
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		JRadioButton rb = (JRadioButton) ob;
		statType = rb.getText();

		// 선택된 라디오 버튼의 이름 비교 후, 맞는 차트 설정
		if (statType.equals("매출합계")) {
			setChart(statType); // 차트 생성 & pShowGraph패널의 카드 레이아웃으로 show()
		} else if (statType.equals("고객수")) {
			setChart(statType);
		}

	}

	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
	public void search() {

		// 테이블 행 화면 리셋
		Connect_DB.clearRows(vd.tmodel.getRowCount(), vd.tmodel);

		// 콤보박스의 값을 가져옴
		// (년도, 월, 일)
		String year = vd.comboYear.getItemAt(vd.comboYear.getSelectedIndex()).toString();
		String month = vd.comboMonth.getItemAt(vd.comboMonth.getSelectedIndex()).toString();
		String day = vd.comboDay.getItemAt(vd.comboDay.getSelectedIndex()).toString();
		if (Integer.parseInt(day) < 10) {
			day = "0".concat(day);
		}

		Connect_DB connect_db = new Connect_DB();
		Vector<String> rows = new Vector<String>(); // 행

		results = new Vector<PosUse>(); // 그래프용 값 저장 백터

		// select 결과 저장
		
		PosUse result = connect_db.findDaySell(year, month, day); // DB select 결과 저장 변수
		results.add(result);
		

		if (result.getDaySellDate() == null) { // 조회 결과 없으면, 알림창 날림
			JOptionPane.showMessageDialog(null, "조회할 데이터가 없습니다.");
		} else { // 조회 결과 있으면, 결과 보이기

			// 테이블 행 세팅
			rows.addElement(result.getDaySellDate());
			rows.addElement(Integer.toString(result.getDayTotalPrice()));
			rows.addElement(Integer.toString(result.getDayTotalcash()));
			rows.addElement(Integer.toString(result.getDayTotalcard()));
			rows.addElement(Integer.toString(result.getDayTotalaccount()));
   
			vd.tmodel.addRow(rows);
			
			// 결과 테이블 띄우기
			vd.spShowTable.setViewportView(vd.tableResult);

			// 디폴트 그래프인 매출합계도 같이 띄워줌
			setChart(statType);
		}
		

	}

	// <라디오버튼 값에 따른 막대 그래프 세팅> 이벤트
	// option : 1 - 매출합계 / 2 - 고객수
	public void setChart(String type) {

		// #차트 생성#
		// [데이터 생성]
		DefaultCategoryDataset dataset;

		// 콤보박스의 값을 가져옴
		// (년도, 월, 일)
		String year = vd.comboYear.getItemAt(vd.comboYear.getSelectedIndex()).toString();
		String month = vd.comboMonth.getItemAt(vd.comboMonth.getSelectedIndex()).toString();
		String day = vd.comboDay.getItemAt(vd.comboDay.getSelectedIndex()).toString();
		if (Integer.parseInt(day) < 10) {
			day = "0".concat(day);
		}

		System.out.println(year+month+day);
		// 현재 콤보박스의 일자에 맞는 시간대별 매출합계, 고객수 받아오기
		Connect_DB connect_db = new Connect_DB();
		Vector<PosUse> resultG = new Vector<PosUse>(); // 그래프용 값 저장 백터

		// select 결과 저장
		if (results != null) { // 조회 버튼 한 번 눌렸다면, 그래프 값 세팅
			System.out.println(year+month+day);
			resultG = connect_db.findDayTimeSell(year, month, day); // DB select 결과 저장 변수
			//resultG = connect_db.findDayTimeSell("2018", "01", "01");
			
		}
		
		// [데이터 세팅]
		// type(통계 분류)에 따라 다르게 세팅됨
		dataset = getGraphDataset(type, resultG);

		// [렌더링 생성]
		final LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		// 렌더링 설정
		// 공통 옵션 정의
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.INSIDE7, TextAnchor.BASELINE_RIGHT);
		Font f = new Font("맑은고딕", Font.BOLD, 17);
		Font axisF = new Font("맑은고딕", Font.PLAIN, 13);

		// 꺾은 선 모양 설정
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setBaseFillPaint(Color.WHITE); // 점 채우기 색상
		renderer.setBaseItemLabelPaint(Color.WHITE); // 값 표시 텍스트 색상
		renderer.setBaseItemLabelFont(f);
		renderer.setBasePositiveItemLabelPosition(p_below);
		renderer.setSeriesPaint(0, new Color(210, 121, 22));
		renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));

		// [plot 생성]
		final CategoryPlot plot = new CategoryPlot();

		// plot 에 데이터 적재
		plot.setDataset(dataset);
		plot.setRenderer(renderer);

		// plot 기본 설정
		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

		// X축 세팅
		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정
		plot.getDomainAxis().setTickLabelPaint(Color.WHITE); // X축 눈금라벨 폰트 색상 조정

		// Y축 세팅
		plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정
		plot.getRangeAxis().setTickLabelPaint(Color.WHITE); // Y축 눈금라벨 폰트 색상 조정
		plot.setBackgroundPaint(Color.DARK_GRAY); // 차트 배경 색 설정

		// [차트 생성]
		// 세팅된 plot을 바탕으로 chart 생성
		final JFreeChart chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.DARK_GRAY);

		// #생성된 차트로 차트 패널 생성#
		ChartPanel chartp = new ChartPanel(chart);
		chartp.setVisible(true);
		chartp.setSize(1120, 368); // 사이즈 지정 필수!

		switch (type) {
		case "매출합계":
			vd.pShowGraph.add("graphTotal", chartp);
			vd.graphCard.show(vd.pShowGraph, "graphTotal");
			break;
		default:
			renderer.setSeriesPaint(0, new Color(75, 84, 147));
			vd.pShowGraph.add("graphCustomerCount", chartp);
			vd.graphCard.show(vd.pShowGraph, "graphCustomerCount");
		}

		vd.pShowGraph.setVisible(true);

	}

	// <타입(매출합계, 고객수)에 따른 그래프 데이터셋 리턴> 메소드
	public DefaultCategoryDataset getGraphDataset(String type, Vector<PosUse> resultG) {

		DefaultCategoryDataset dataset = null;

		if (results != null) { // 조회 결과 있을 때만 그래프 값 설정
			System.out.println("그래프 값 설정!!!!!");
			String time[] = { "00시", "01시", "02시", "03시", "04시", "05시", // 시간(그래프용)
					"06시", "07시", "08시", "09시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시", "18시", "19시",
					"20시", "21시", "22시", "23시" };

			Vector<String> sell_time = new Vector<String>(); // 판매 시간 (한 행)
			Vector<Integer> values = new Vector<Integer>(); // 분류별 값 (한 행)

			dataset = new DefaultCategoryDataset();

			int size = resultG.size();
			System.out.println(size);
			boolean flag = false; // 시간대 별 값 받아옴 여부

			switch (type) {
			case "매출합계":
				for (int i = 0; i < 24; i++) {
					for (int j = 0; j < size; j++) {
						System.out.println(time[i]);
						System.out.println(resultG.get(j).getClockTime());
						if (time[i].equals(resultG.get(j).getClockTime())) {
							dataset.addValue(resultG.get(j).getClockTotalPrice(), type, time[i]);
							break;
						} else {
							dataset.addValue(0, type, time[i]);
						}
					}
				}
				break;
			default: // 고객 수
				for (int i = 0; i < 24; i++) {
					for (int j = 0; j < size; j++) {
						System.out.println(time[i]);
						System.out.println(resultG.get(j).getClockTime());
						if (time[i].equals(resultG.get(j).getClockTime())) {
							dataset.addValue(resultG.get(j).getClockTotalaccount(), type, time[i]);
							break;
						} else {
							dataset.addValue(0, type, time[i]);
						}
					}
				}
				break;
			}
		}

		return dataset;
	}
	
}
