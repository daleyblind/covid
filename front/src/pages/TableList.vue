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

export default {
  components: {
    Bullet
  },
  data() {
    return {
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
    async findBulletList() {
      await this.$api(this.$prefixURL + "/bullet", "get").then(response => {
        this.bullet.data = response;
      });
    },
  },
  created() {
    this.findBulletList();
  },
};
</script>
<style>
</style>
