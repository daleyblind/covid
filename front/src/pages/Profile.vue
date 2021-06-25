<template>
  <div class="row">
    <div class="col-lg-12">
      <user-card :user="user"></user-card>
    </div>
  </div>
</template>
<script>
import UserCard from './Profile/UserCard'

export default {
  components: {UserCard},
  data() {
    return {
      user: {
        fullName: 'Jang Hyun',
        title: 'Web Developer',
        description: `개발을 좋아하는 청년, 장현입니다.
이제 막 2년 차에 돌입하는 햇병아리이지만
현재 끊임없이 공부 중이며, 새로운 기술을 배우는 것을 좋아합니다.
물론 스터디와 코딩 리뷰도 좋아합니다.`,
      }
    }
  }
}
</script>
<style>
</style>
