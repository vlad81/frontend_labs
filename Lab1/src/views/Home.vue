<template>
    <div>
        <Headbar :render="true"/>
        <div class="container mt-3">
            <h2>Home page</h2>
            <p class="text-left mt-3">
                This application is a simple blog designed for learning aims. Basic functions:
                <ul>
                    <li>Create an account</li>
                    <li>Add own article</li>
                    <li>Comment different articles</li>
                </ul>
            </p>
        </div>
    </div>
</template>

<script>
import Headbar from '@/components/Headbar'
export default {
    components : {
        Headbar
    }
}
</script>

<style scoped>
.container {
    width: 70%;
}
</style>