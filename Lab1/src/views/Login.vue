<template>
    <div>
        <Headbar :render="true"/>
        <div class="container mt-4">
            <div class="row">
                <div class="col-sm-6 mx-auto">
                    <form >
                        <div class="form-group">
                            <label for="email">Email address:</label>
                            <input type="email" class="form-control" placeholder="Email" id="email" v-model="username">
                        </div>

                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" placeholder="Password" id="pwd" v-model="password">
                        </div>

                        <button type="submit" class="btn btn-primary" style="background-color: #e08916;">Log in</button>
                        <div class="form-group">
                            <router-link  to="/register"> Create an account </router-link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Headbar from '@/components/Headbar'

export default {
    components : {
        Headbar
    },
    methods: {
        login: function () {
            let email = this.email 
            let password = this.password
            this.$store.dispatch('login', { email, password })
                .then(() => this.$router.push('/'))
                .catch(err => console.log(err))
      }
    }
    
}
</script>
