function tegnDiagram() {

	const FARGE_SUR = '#CD3337';
	const FARGE_MIDDELS = '#CF7C34';
	const FARGE_GLAD = '#30A82A';

	var dataArray = formatData(frekvensTabell, 15, 4);
	var button = document.getElementById('start-stopp');
	var chartDiv = document.getElementById('chart_div');
	var data = google.visualization.arrayToDataTable(dataArray);

	var classicOptions = {
		width : 1250,
		series : {
			0 : {
				targetAxisIndex : 0
			}
		// 1: {targetAxisIndex: 1}
		},
		title : 'Tilbakemeldinger',
		vAxes : {
			// Adds titles to each axis.
			0 : {
				title : 'Antall'
			}
		},
		hAxes : {
			// Adds titles to each axis.
			0 : {
				title : 'Minutter'
			}
		},
		axes : {
			y : {
				distance : {
					side : 'right',
					label : 'Antall'
				}
			// Right y-axis.
			}
		},
		colors : [ FARGE_SUR, FARGE_MIDDELS, FARGE_GLAD ]
	};

	function startHandeler() {
		button.innerText = 'Stop';
		button.onclick = stopHandeler;
	}

	function stopHandeler() {
		button.innerText = 'Start';
		button.onclick = startHandeler;
	}

	function drawClassicChart() {
		var classicChart = new google.visualization.ColumnChart(chartDiv);
		classicChart.draw(data, classicOptions);
		startHandeler();
	}

	/*
	 * frekvensTabell: streng fra servlet. antallSlotter: hvor mange slotter vi
	 * skal ha i diagrammet. slottBredde: hvor mange minutter hver slott skal
	 * representere.
	 */
	function formatData(frekvensTabell, antallSlotter, slottBredde) {
		let dataArr = lagDataArray(antallSlotter);
		let frekvensArr = lagFrekvensArray(frekvensTabell);

		for (let i = 0; i < antallSlotter; i++) {
			let label = "" + (i + 1) * slottBredde + "";
			let daarligIndex = i * slottBredde;
			let middelsIndex = i * slottBredde + 1;
			let godIndex = i * slottBredde + 2;
			dataArr[i + 1][0] = label;
			dataArr[i + 1][1] = parseInt(frekvensArr[daarligIndex]);
			dataArr[i + 1][2] = parseInt(frekvensArr[middelsIndex]);
			dataArr[i + 1][3] = parseInt(frekvensArr[godIndex]);
		}
		return dataArr;
	}

	function lagDataArray(antallSlotter) {
		// Legger til en ekstra på grunn av "labels"
		let res = new Array(antallSlotter + 1);
		res[0] = [ 'Minutter', 'Daarlig', 'Middels', 'Bra' ];
		for (let i = 1; i < antallSlotter + 1; i++) {
			res[i] = new Array(4);
		}
		return res;
	}
	function lagFrekvensArray(frekvensTabell) {
		let fSliced = frekvensTabell.slice(1, -1);
		return fSliced.split(", ");
	}

	drawClassicChart();
};