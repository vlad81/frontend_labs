<template>
    <div>
    <Headbar :render="true"/>
        <div>
            <ul id="array-rendering">
                <li v-for="item in items">
                    <h3 class="text-left"><button><font size="20" face="Arial" color="#273042">{{ item.name }}</font></button></h3>
                </li>
            </ul>
        </div>

        <div class="text-left"><button type="submit" class="btn btn-primary" style="background-color: #e08916;" @click="submit">Add new article</button></div>
    </div>
</template>

<script>
import Headbar from '@/components/Headbar'

export default {
    components : {
        Headbar
    },
    data() {
        return {
            "items": [{ name: 'Test article 1' }, { name: 'Test article 2' }]
        }
    }
}
</script>