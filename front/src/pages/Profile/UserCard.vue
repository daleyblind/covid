<template>
  <card type="user">
    <p class="card-text">
    </p>
    <div class="author">
      <div class="block block-one"></div>
      <div class="block block-two"></div>
      <div class="block block-three"></div>
      <div class="block block-four"></div>
      <div>
        <img class="avatar" src="img/anime3.png" alt="...">
        <h5 class="title">{{user.fullName}}</h5>
      </div>
      <p class="description">
        {{user.title}}
      </p>
    </div>
    <p></p>
    <pre class="card-description">
      {{user.description}}
    </pre>
    <div slot="footer" class="button-container">
      <base-button icon round class="btn-github" @click="openGithub">
        <i class="fab fa-github"></i>
      </base-button>
    </div>
  </card>
</template>
<script>
  export default {
    props: {
      user: {
        type: Object,
        default: () => {
          return {};
        }
      }
    },
    methods: {
      openGithub() {
        window.open('https://github.com/daleyblind/covid');
      },
    },
  }
</script>
<style>
.card-description {
  text-align: center;
  font-size: 1rem;
}
</style>
