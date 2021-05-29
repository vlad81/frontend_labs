<template>
    <div>
    <Headbar :render="true"/>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-6 mx-auto">
                <form >

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="name" class="form-control" placeholder="Enter name" id="name" v-model="username">
                    </div>

                    <div class="form-group">
                        <label for="email">Email address:</label>
                        <input type="email" class="form-control" placeholder="Email" id="email" v-model="email">
                    </div>
                    
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" placeholder="Enter password" id="pwd" v-model="password">
                    </div>

                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" placeholder="Confirm password" id="pwd_confirm" v-model="confirm_password">
                    </div>

                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select class="form-control" id="gender" v-model="gender" placeholder="Gender">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birthday">Birht date:</label>
                        <input type="date" class="form-control" placeholder="Birth date" id="birthday" v-model="birthday_date">
                    </div>

                    <button type="submit" class="btn btn-primary" style="background-color: #e08916;" @click="submit">Create an account</button>
                </form>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import Headbar from '@/components/Headbar'

export default {
    components:{
        Headbar
    }
}
</script>