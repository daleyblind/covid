<template>
  <div class="row">
    <div class="col-12">
      <card :title="title">
        <div class="table-responsive">
          <div class="table-responsive">
            <table class="table tablesorter text-primary">
              <tr>
                <th>제목</th>
                <td>
                  <base-input v-model="bullet.title" :value="`{{bullet.title}}`" :disabled=isNotModifying></base-input>
                </td>
              </tr>
              <tr>
                <th>작성자</th>
                <td>
                  <base-input v-model="bullet.writer" :value="`{{bullet.writer}}`" :disabled=isNotModifying></base-input>
                </td>
              </tr>
              <tr>
                <th>내용</th>
                <td>
                  <base-input>
                    <textarea v-model="bullet.content" class="form-control" rows="3" :disabled=isNotModifying>{{bullet.content}}</textarea>
                  </base-input>
                </td>
              </tr>
              <tr>
                <th>작성일</th>
                <td>{{$formatDate(this.bullet.create)}}</td>
              </tr>
              <tr>
                <th>수정일</th>
                <td>{{$formatDate(this.bullet.update)}}</td>
              </tr>
            </table>
            <div class="float-right">
              <base-button class="animation-on-hover" type="success" @click="toList">List</base-button>
              <base-button class="animation-on-hover" type="warning" @click="removeBullet">Delete</base-button>
              <base-button class="animation-on-hover" type="primary" @click="modifyBullet">Modify</base-button>
            </div>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Bullet",
  data() {
    return {
      title: "상세 페이지",
      bullet: {
        no: this.$route.params.no,
        title: '',
        writer: '',
        content: '',
        create: '',
        update: '',
      },
      isNotModifying: true,
    };
  },
  methods: {
    toList() {
      this.$router.push({name: '건의 게시판'});
    },
    async findBullet() {
      await this.$api(this.$prefixURL + "/bullet/" + this.bullet.no, "get").then(response => {
        this.bullet = response;
      });
    },
    async removeBullet() {
      await this.$api(this.$prefixURL + "/bullet/" + this.bullet.no, "delete").then(response => {
        if (response === true) {
          this.$router.push({name: '건의 게시판'});
        }
      });
    },
    async modifyBullet() {
      if (!this.isNotModifying) {
        if (!this.isEmptyValue(this.bullet)) {
          this.$notify({
            type: 'info',
            message: '모든 칸을 입력해 주시길 바랍니다',
          });
          return;
        }
        await this.$api(this.$prefixURL + "/bullet", "put", this.bullet).then(response => {
          if (response === true) {
            this.$router.push({name: '건의 게시판'});
          }
        });
      }
      this.isNotModifying = false;
    },
    isEmptyValue(data) {
      let flag = true;
      for (let key in data) {
        if (data.hasOwnProperty(key)) {
          if (data[key] === '') {
            flag = false;
            break;
          }
        }
      }
      return flag;
    },
  },
  created() {
    this.findBullet();
  }
}
</script>

<style scoped>

</style>
