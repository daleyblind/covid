<template>
    <div class="row">
      <div class="col-12">
        <card :title="bullet.title">
          <div class="table-responsive">
            <table class="table tablesorter">
              <thead class="text-primary">
                <tr>
                  <th v-for="column in bullet.columns">{{column}}</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="bullet in bullet.data">
                  <td>{{bullet.no}}</td>
                  <td><router-link :to="`/bullet/${bullet.no}`">{{bullet.title}}</router-link></td>
                  <td>{{bullet.writer}}</td>
                  <td>{{$formatDate(bullet.create)}}</td>
                  <td>{{$formatDate(bullet.update)}}</td>
                </tr>
              </tbody>
            </table>
            <paginate
              v-model="page"
              :page-count="totalPage"
              :page-range="3"
              :margin-pages="2"
              :click-handler="findBulletList"
              :prev-text="'이전'"
              :next-text="'다음'"
              :container-class="'pagination'"
              :page-class="'page-item'">
            </paginate>
          </div>
          <div class="float-right">
            <router-link :to="`/write`">
              <base-button class="animation-on-hover" type="info">Write</base-button>
            </router-link>
          </div>
        </card>
      </div>
    </div>
</template>
<script>
import Bullet from "@/pages/Bullet";
import Paginate from "vuejs-paginate";

export default {
  components: {
    Bullet,
    Paginate
  },
  data() {
    return {
      page: 1,
      totalPage: 1,
      bullet: {
        title: "건의 게시판",
        columns: ["NO", "TITLE", "WRITER", "CREATE", "UPDATE"],
        data: [{
          no: 0,
          title: '',
          writer: '',
          create: '',
          update: '',
        }]
      },
    };
  },
  methods: {
    async findBulletList(pageNum) {
      await this.$api(this.$prefixURL + "/bullet?page="+pageNum, "get").then(response => {
        this.totalPage = response.total;
        this.bullet.data = response.content;
      });
    },
  },
  created() {
    this.findBulletList(this.page);
  },
};
</script>
<style>
</style>
