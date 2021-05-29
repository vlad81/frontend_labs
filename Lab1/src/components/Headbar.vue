<template>
   <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e08916;">
       <a class="navbar-brand" href="#"> Blog </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" v-if="render">
      <li class="nav-item">
        <li class="nav-item">
         <router-link class="nav-link" to="/login"> Log in </router-link>
        </li>
        <router-link class="nav-link" to="/"> Home </router-link>
        <li class="nav-item">
          <router-link class="nav-link" to="/articles"> Articles </router-link>
      </li>
    </ul>
  </div>
</nav> 
</template>

<script>
export default {
    props: ["render"],
}
</script>