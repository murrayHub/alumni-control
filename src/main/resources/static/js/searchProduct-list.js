Vue.component('search-product-list', {
    props: ['item','good_href'],
    template: '#searchProducts-list',
});

var productList;
// 获取商品集合
sendRequestTB(ctx+"/goodsInfo/goodsSearchList", {goodsKeyWords:$("#goodsKeyWords").val()}, function (back) {
    productList=back;
});
console.info(productList);

var search = new Vue({
    el:'#searchWrap',
    data: function() {
        return {
            item: {},
            productArr:productList
        }
    },
    methods:{
        good_href: function(val){
            return '/goodsInfo/goodsDetail?goodsId='+ val;
        }
    }
});