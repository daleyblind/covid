<template>
  <div class="row">
    <div class="col-12">
      <card :title="title">
        <div class="table-responsive">
          <div class="table-responsive">
            <table class="table tablesorter text-primary">
              <tr>
                <th>제목</th>
                <td><base-input v-model="bullet.title" placeholder="제목을 입력해주세요"></base-input></td>
              </tr>
              <tr>
                <th>작성자</th>
                <td><base-input v-model="bullet.writer"></base-input></td>
              </tr>
              <tr>
                <th>내용</th>
                <td>
                  <base-input>
                    <textarea v-model="bullet.content" class="form-control" rows="3" placeholder="내용을 입력해주세요"></textarea>
                  </base-input>
                </td>
              </tr>
            </table>
            <div class="float-right">
              <base-button class="animation-on-hover" type="success" @click="toList">List</base-button>
              <base-button class="animation-on-hover" type="info" @click="writeBullet">Write</base-button>
            </div>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Write",
  data() {
    return {
      title: "Write",
      bullet: {
        title: '',
        writer: '',
        content: '',
      },
    }
  },
  methods: {
    toList() {
      this.$router.push({name: '건의 게시판'});
    },
    async writeBullet() {
      if (!this.isEmptyValue(this.bullet)) return;
      await this.$api(this.$prefixURL + "/bullet", "post", this.bullet).then(response => {
        if (response === true) {
          this.$router.push('bullet');
        }
      });
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
}
</script>

<style scoped>

</style>
