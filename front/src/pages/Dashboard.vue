<template>
  <div>
    <div class="row">
      <div class="col-12">
        <card type="chart">
          <template slot="header">
            <div class="row">
              <div class="col-sm-6" :class="isRTL ? 'text-right' : 'text-left'">
                <h5 class="card-category">Today Statistics</h5>
                <h2 class="card-title">{{todayTitle}}</h2>
              </div>
              <div class="col-sm-6">
                <div class="btn-group btn-group-toggle"
                     :class="isRTL ? 'float-left' : 'float-right'"
                     data-toggle="buttons">
                  <label v-for="(option, index) in bigLineChartCategories"
                         :key="option"
                         class="btn btn-sm btn-primary btn-danger"
                         :class="{active: bigLineChart.activeIndex === index}"
                         :id="index">
                    <input type="radio"
                           @click="initBigChart(index)"
                           name="options" autocomplete="off"
                           :checked="bigLineChart.activeIndex === index">
                    {{option}}
                  </label>
                </div>
              </div>
            </div>
          </template>
          <div class="chart-area">
            <line-chart style="height: 100%"
                        ref="bigChart"
                        chart-id="big-line-chart"
                        :chart-data="bigLineChart.chartData"
                        :gradient-colors="bigLineChart.gradientColors"
                        :gradient-stops="bigLineChart.gradientStops"
                        :extra-options="bigLineChart.extraOptions">
            </line-chart>
          </div>
        </card>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-4" :class="{'text-right': isRTL}">
        <card type="chart">
          <template slot="header">
            <h5 class="card-category">누적 확진자</h5>
            <h3 class="card-title"><i class="tim-icons icon-volume-98 text-warning"></i>
              {{covid.decideCnt | makeComma}} <span class="text-warning">▲{{dailyDecideCnt | makeComma}}</span>
            </h3>
          </template>
          <div class="chart-area">
            <line-chart style="height: 100%"
                        chart-id="purple-line-chart"
                        :chart-data="decideChart.chartData"
                        :gradient-colors="decideChart.gradientColors"
                        :gradient-stops="decideChart.gradientStops"
                        :extra-options="decideChart.extraOptions">
            </line-chart>
          </div>
        </card>
      </div>
      <div class="col-lg-4" :class="{'text-right': isRTL}">
        <card type="chart">
          <template slot="header">
            <h5 class="card-category">누적 격리해제</h5>
            <h3 class="card-title"><i class="tim-icons icon-send text-success"></i>
              {{covid.clearCnt | makeComma}} <span class="text-success">▲{{dailyClearCnt | makeComma}}</span>
            </h3>
          </template>
          <div class="chart-area">
            <line-chart style="height: 100%"
                        chart-id="green-line-chart"
                        :chart-data="clearChart.chartData"
                        :gradient-colors="clearChart.gradientColors"
                        :gradient-stops="clearChart.gradientStops"
                        :extra-options="clearChart.extraOptions">
            </line-chart>
          </div>
        </card>
      </div>
      <div class="col-lg-4" :class="{'text-right': isRTL}">
        <card type="chart">
          <template slot="header">
            <h5 class="card-category">누적 사망자</h5>
            <h3 class="card-title"><i class="tim-icons icon-alert-circle-exc text-danger"></i>
              {{covid.deathCnt | makeComma}} <span class="text-danger">▲{{dailyDeathCnt | makeComma}}</span>
            </h3>
          </template>
          <div class="chart-area">
            <line-chart style="height: 100%"
                        chart-id="red-line-chart"
                        :chart-data="deathChart.chartData"
                        :gradient-colors="deathChart.gradientColors"
                        :gradient-stops="deathChart.gradientStops"
                        :extra-options="deathChart.extraOptions">
            </line-chart>
          </div>
        </card>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12" :class="{'text-right': isRTL}">
        <card type="chart">
          <template slot="header">
            <h5 class="card-category">COVID-19 Inspection Statistics</h5>
            <h3 class="card-title"><i class="tim-icons icon-zoom-split text-info "></i> COVID-19 검사 통계</h3>
          </template>
          <div class="chart-area">
            <bar-chart style="height: 100%"
                       chart-id="blue-bar-chart"
                       :chart-data="blueBarChart.chartData"
                       :gradient-stops="blueBarChart.gradientStops"
                       :extra-options="blueBarChart.extraOptions">
            </bar-chart>
          </div>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import LineChart from '@/components/Charts/LineChart';
import BarChart from '@/components/Charts/BarChart';
import * as chartConfigs from '@/components/Charts/config';
import config from '@/config';

export default {
    components: {
      LineChart,
      BarChart
    },
    data() {
      return {
        bigLineChart: {
          allData: [
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
          ],
          activeIndex: 0,
          chartData: {
            datasets: [{ }],
            labels: ['', '', '', '', '', '', '', '', '', '', '', ''],
          },
          extraOptions: chartConfigs.purpleChartOptions,
          gradientColors: config.colors.primaryGradient,
          gradientStops: [1, 0.4, 0],
          categories: []
        },
        clearChart: {
          extraOptions: chartConfigs.blueChartOptions,
          chartData: {
            labels: ['', '', '', '', '', ''],
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.teal,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.teal,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.teal,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: [0, 0, 0, 0, 0, 0],
            }]
          },
          gradientColors: config.colors.primaryGradient,
          gradientStops: [1, 0.2, 0],
        },
        decideChart: {
          extraOptions: chartConfigs.orangeChartOptions,
          chartData: {
            labels: ['', '', '', '', ''],
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.warn,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.warn,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.warn,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: [0, 0, 0, 0, 0],
            }]
          },
          gradientColors: ['rgba(255,134,121,0.3)', 'rgba(66,134,121,0.0)', 'rgba(66,134,121,0)'],
          gradientStops: [1, 0.4, 0],
        },
        blueBarChart: {
          extraOptions: chartConfigs.barChartOptions,
          chartData: {
            labels: ['누적 검사 수', '누적 검사 완료 수', '검사 진행 수', '결과 음성 수', '확진자 수', '치료 중 환자 수'],
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.info,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: [0,0,0,0,0,0],
            }]
          },
          gradientColors: config.colors.primaryGradient,
          gradientStops: [1, 0.4, 0],
        },
        deathChart: {
          extraOptions: chartConfigs.greenChartOptions,
          chartData: {
            labels: ['', '', '', '', ''],
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.danger,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.danger,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.danger,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: [0, 0, 0, 0, 0],
            }]
          }
        },
        covid: {
          seq: 0,
          stateDt: '',
          decideCnt: 0,
          clearCnt: 0,
          examCnt: 0,
          deathCnt: 0,
          careCnt: 0,
          resultNegCnt: 0,
          accExamCnt: 0,
          accExamCompCnt: 0,
        },
        todayTitle: '',
        dailyDecideCnt: 0,
        dailyClearCnt: 0,
        dailyDeathCnt: 0,
      }
    },
    computed: {
      enableRTL() {
        return this.$route.query.enableRTL;
      },
      isRTL() {
        return this.$rtl.isRTL;
      },
      bigLineChartCategories() {
        return ['확진자', '격리해제', '사망자'];
      }
    },
    methods: {
      initBigChart(index) {
        let color;
        if (index === 0) {
          this.todayTitle = '일일 확진자'
          color = config.colors.warn;
        } else if (index === 1) {
          this.todayTitle = '일일 격리해제'
          color = config.colors.teal;
        } else {
          this.todayTitle = '일일 사망자'
          color = config.colors.danger;
        }
        let chartData = {
          datasets: [{
            fill: true,
            borderColor: color,
            borderWidth: 2,
            borderDash: [],
            borderDashOffset: 0.0,
            pointBackgroundColor: color,
            pointBorderColor: 'rgba(255,255,255,0)',
            pointHoverBackgroundColor: color,
            pointBorderWidth: 20,
            pointHoverRadius: 4,
            pointHoverBorderWidth: 15,
            pointRadius: 4,
            data: this.bigLineChart.allData[index]
          }],
        }
        this.$refs.bigChart.updateGradients(chartData);
        this.bigLineChart.chartData = chartData;
        this.bigLineChart.activeIndex = index;
      },
      async showCovidList() {
        let covidArray = {};
        await this.$api(this.$prefixURL + "/dashboard", "get").then(function (data) {
          covidArray = data;
        });
        this.covid = covidArray[0];
        const result = covidArray.sort((a, b) => {
          let first = a.seq;
          let second = b.seq;
          if (first > second) return 1;
          else if (first === second) return 0;
          else return -1;
        });
        this.drawBlueChart(covidArray[0]);
        let accArray = [];
        if (result.length > 5) {
          accArray = result.slice(result.length - 5);
        } else {
          accArray = result;
        }
        this.drawDecideChart(accArray);
        this.drawClearChart(accArray);
        this.drawDeathChart(accArray);
        let bigChartArray = [];
        let dailyDecideArray = this.calculateDaily(result.map(v => v.decideCnt));
        let dailyClearArray = this.calculateDaily(result.map(v => v.clearCnt));
        let dailyDeathArray = this.calculateDaily(result.map(v => v.deathCnt));
        this.dailyDecideCnt = dailyDecideArray[dailyDecideArray.length - 1];
        this.dailyClearCnt = dailyClearArray[dailyClearArray.length - 1];
        this.dailyDeathCnt = dailyDeathArray[dailyDeathArray.length - 1];
        bigChartArray.push(dailyDecideArray);
        bigChartArray.push(dailyClearArray);
        bigChartArray.push(dailyDeathArray);
        this.bigLineChart.allData = bigChartArray;
        let labels = result.map(v => this.formatDate(v.stateDt));
        labels.shift();
        this.bigLineChart.chartData.labels = labels;
        this.initBigChart(0);
      },
      drawBlueChart(obj) {
        this.blueBarChart = {
          extraOptions: chartConfigs.barChartOptions,
          chartData: {
            labels: ['누적 검사', '누적 검사 완료', '검사 진행', '결과 음성', '확진자', '치료 중 환자'],
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.info,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: [obj.accExamCnt, obj.accExamCompCnt, obj.examCnt, obj.resultNegCnt, obj.decideCnt, obj.careCnt],
            }]
          },
          gradientColors: config.colors.primaryGradient,
          gradientStops: [1, 0.4, 0],
        }
      },
      drawDecideChart(arr) {
        let labels = arr.map(v => this.formatDate(v.stateDt));
        let data = arr.map(v => v.decideCnt);
        this.decideChart = {
          extraOptions: chartConfigs.orangeChartOptions,
          chartData: {
            labels: labels,
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.warn,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.warn,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.warn,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: data,
            }]
          },
          gradientColors: ['rgba(255,134,121,0.3)', 'rgba(66,134,121,0.0)', 'rgba(66,134,121,0)'],
          gradientStops: [1, 0.4, 0],
        }
        this.decideChart.extraOptions.scales.yAxes[0].ticks.suggestedMin = data[0];
        this.decideChart.extraOptions.scales.yAxes[0].ticks.suggestedMax = data[data.length - 1];
      },
      drawClearChart(arr) {
        let labels = arr.map(v =>this.formatDate(v.stateDt));
        let data = arr.map(v => v.clearCnt);
        this.clearChart = {
          extraOptions: chartConfigs.blueChartOptions,
          chartData: {
            labels: labels,
            datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.teal,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.teal,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.teal,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: data,
            }]
          }
        };
        this.clearChart.extraOptions.scales.yAxes[0].ticks.suggestedMin = data[0];
        this.clearChart.extraOptions.scales.yAxes[0].ticks.suggestedMax = data[data.length - 1];
      },
      drawDeathChart(arr) {
        let labels = arr.map(v => this.formatDate(v.stateDt));
        let data = arr.map(v => v.deathCnt);
        this.deathChart = {
          extraOptions: chartConfigs.greenChartOptions,
            chartData: {
            labels: labels,
              datasets: [{
              label: "count",
              fill: true,
              borderColor: config.colors.danger,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.danger,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: config.colors.danger,
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: data,
            }]
          }
        }
        this.deathChart.extraOptions.scales.yAxes[0].ticks.suggestedMin = data[0];
        this.deathChart.extraOptions.scales.yAxes[0].ticks.suggestedMax = data[data.length - 1];
      },
      formatDate(value) {
        let date = '';
        if (value.length === 8) {
          date = value.slice(4).replace(/(.{2})/g, '$1/').replace(/\/$/, '');
        }
        return date;
      },
      calculateDaily(arr) {
        let result = [];
        for (let i = 0; i < arr.length; i++) {
          if (i === arr.length - 1) break;
          result.push(arr[i + 1] - arr[i]);
        }
        return result;
      }
    },
    created() {
      this.showCovidList();
    },
    beforeDestroy() {
      if (this.$rtl.isRTL) {
        this.i18n.locale = 'en';
        this.$rtl.disableRTL();
      }
    }
  };
</script>
<style>
</style>
