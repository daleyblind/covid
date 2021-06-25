<template>
  <div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="card-header mb-5">
          <h5 class="card-category">COVID-19 Information</h5>
          <h3 class="card-title">COVID-19 정보</h3>
        </div>
        <div class="card-body">
          <div>
            <table id="data_table">
              <caption>
                <span class="hdn">코로나바이러스감염증-19 기본정보표 - 병원체, 전파 경로, 잠복기, 진단 기준, 증상, 치료, 치명률, 예방으로 구성</span>
              </caption>
              <colgroup>
                <col style="width:20%">
                <col style="width:80%">
              </colgroup>
              <tbody>
              <tr>
                <th scope="row">병원체</th>
                <td class="ta_l">
                  <ul>
                    <li>Severe Acute Respiratory Syndrome-Coronavirus-2(SARS-CoV-2)</li>
                    <li>Coronaviridae family, Betacoronavirus genus Sarbecovirus subgenus에 속함</li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">전파 경로</th>
                <td class="ta_l">
                  <ul>
                    <li>주된 전파경로는 감염자의 호흡기 침방울(비말)에 의한 전파</li>
                    <li>사람 간에 전파되며, 대부분의 감염은 감염자가 기침, 재채기, 말하기, 노래 등을할 때 발생한 호흡기 침방울(비말)을다른사람이
                      밀접접촉(주로 2m 이내)하여 발생
                    </li>
                    <li>현재까지 연구결과에 의하면, 비말 이외, 표면접촉*, 공기 등을 통해서도 전파가 가능하나, 공기전파는 의료기관의에어로졸 생성 시술**,
                      밀폐된공간에서 장시간 호흡기 비말을 만드는환경***등 특정 환경에서 제한적으로 전파되는 것으로 알려짐
                      <p class="mgt12">* 감염된 사람과의 직접 접촉(악수 등) 또는 매개체(오염된 물품이나 표면)를 만진 후, 손을 씻기 전 눈, 코, 입 등을
                        만짐으로 바이러스 전파
                      </p>
                      <p class="mgt8">** 에어로졸 생성 시술: 기관지 내시경 검사, 객담 유도, 기관삽관, 심폐소생술, 개방된 객담 흡입, 흡입기 등</p>
                      <p class="mgt8">*** 환기가 부적절하게 이루어진 노래방, 커피숍, 주점, 실내 운동시설 등에서 감염자와 같이있거나 감염자가
                        떠난 즉시 그 밀폐공간을 방문한 경우
                      </p>
                    </li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">잠복기</th>
                <td class="ta_l">
                  <ul>
                    <li>1~14일(평균 5~7일)</li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">진단 기준</th>
                <td class="ta_l">
                  <ul>
                    <li>환자 : 임상양상에 관계없이 진단을 위한 검사기준에 따라 코로나19 감염이 확인된 자</li>
                    <li>진단을 위한 검사 기준
                      <ul>
                        <li>코로나19 유전자(PCR) 검출</li>
                        <li>바이러스 분리</li>
                      </ul>
                    </li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th>증상</th>
                <td>
                  <ul>
                    <li>주요증상 및 징후
                      <ul>
                        <li>- 임상 증상은 무증상, 경증, 중등증, 중증까지 다양</li>
                        <li>- 주요 증상으로는 발열(37.5℃ 이상), 기침, 호흡곤란, 오한, 근육통, 두통, 인후통, 후각·미각소실
                          <p>- 그 외에 피로, 식욕감소, 가래, 소화기증상(오심, 구토, 설사 등), 혼돈, 어지러움, 콧물이나 코막힘,
                            객혈, 흉통, 결막염, 피부 증상 등이 다양하게 나타남
                          </p>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">치료</th>
                <td class="ta_l">
                  <ul class="s_listin_mdot mgt0">
                    <li>특이치료제는 없으며 증상에 따른 해열제, 수액공급, 진해제 등 대증치료</li>
                    <li>호흡 곤란 시 산소를 공급하고 필요한 경우에는 기계호흡이나 체외막 산소공급 등의 처치를 시행</li>
                    <li>산소 치료가 필요한 환자에서 렘데시비르의 효과가 일부 확인되어 우리나라를 포함한 여러 나라에서 긴급 승인이 되었거나,
                      긴급 승인을 준비 중임특이적인 항바이러스제 없음
                    </li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">치명률</th>
                <td class="ta_l">
                  <ul class="s_listin_mdot mgt0">
                    <li>전세계 치명률은 지역, 인구집단연령 구조, 감염 상태 및 기타 요인에 의해 0.1~25%로 다양함</li>
                    <li>전세계 치명률은 0.00%~1.63% (중위값 0.27%)로 보정 후 0.00~1.54%(중위값 0.23%)(Bulletin of the World Health Organization. 2021)
                      <a href="https://www.who.int/bulletin/volumes/99/1/20-265892/en/" target="_blank">
                        (https://www.who.int/bulletin/volumes/99/1/20-265892/en/)
                      </a>
                    </li>
                  </ul>
                </td>
              </tr>
              <tr>
                <th scope="row">예방</th>
                <td class="ta_l">
                  <ul class="s_listin_mdot mgt0">
                    <li>식약처에서 승인된 코로나19 백신(향후 업데이트 예정)</li>
                    <li>올바른 손씻기</li>
                    <li>기침 예절 준수</li>
                    <li>씻지 않은 손으로 눈, 코, 입 만지지 않기</li>
                    <li>주위 환경을 자주 소독하고 환기하기</li>
                  </ul>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div>
            <h4 class="s_title_1">코로나 바이러스 분류 및 특성</h4>
            <h5 class="s_title_in3">개요</h5>
            <ul>
              <li>코로나 바이러스(CoV)는 사람과 다양한 동물에 감염될 수 있는 바이러스로서 유전자 크기 27~32kb의 RNA 바이러스</li>
            </ul>
            <h5 class="s_title_in3">분류</h5>
            <ul class="s_listin_dot">
              <li>코로나 바이러스과에는 4개의 속(알파, 베타, 감마, 델타)이 있음
                <ul class="s_listin_bar">
                  <li>알파 · 베타 : 사람과 동물에게 감염</li>
                  <li>감마 · 델타 : 동물에게 감염</li>
                </ul>
              </li>
            </ul>
            <h5>형태</h5>
            <ul>
              <li>형태는 코로나 바이러스의 명명과 같이 전자현미경 관찰시 구형
                <ul>
                  <li>외부 spike 단백질이 특징적인 크라운형태</li>
                </ul>
              </li>
            </ul>
            <h5>사람감염 코로나 바이러스</h5>
            <ul>
              <li>사람감염 코로나 바이러스는 현재까지 6종류가 알려져 있음
                <ul>
                  <li>감기를 일으키는 유형(229E, OC43, NL63, HKU1)</li>
                  <li>중증폐렴을 일으킬 수 있는 유형(SARS-CoV, MERS-CoV) </li>
                </ul>
              </li>
            </ul>
            <h6 class="float-right">출처 :
              <a href="http://ncov.mohw.go.kr/baroView.do?brdId=4&brdGubun=41" target="_blank">
                http://ncov.mohw.go.kr/baroView.do?brdId=4&brdGubun=41
              </a>
            </h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
#data_table th, #data_table td {
  border: 1px solid white;
  color: white;
}
</style>
