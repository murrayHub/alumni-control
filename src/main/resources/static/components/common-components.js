;(function($){
    // top-header
    Vue.component('top-header', { template: '#top-header-dom' });
    // bottom-footer
    Vue.component('bottom-footer', { template: '#bottom-footer-dom' });

    // product page list wrap
    Vue.component('product-wrap', {
        props: [ 'products' ],
        template: '#product-list-wrap'
    });

    // auction child element dom
    Vue.component('auction-a-element', {
        props: [ 'product' ],
        template: '#product-auction-item',
        data: function() {
            return {
                days: '0',
                hours: '0',
                minutes: '0',
                seconds: '0'
            }
        },
        methods: {
            timer: function(initDiff){
                var that = this;
                window.setInterval(function () {
                    console.log('执行函数-', initDiff);
                    // 时间默认值
                    var day = 0,
                        hour = 0,
                        minute = 0,
                        second = 0;
                    if(initDiff > 0) {
                        console.log('执行判断==');
                        day = Math.floor(initDiff / (60 * 60 * 24));
                        hour = Math.floor(initDiff / (60 * 60)) - (day * 24);
                        minute = Math.floor(initDiff / 60) - (day * 24 * 60) - (hour * 60);
                        second = Math.floor(initDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                    }
                    if( minute <= 9 ) minute = '0' + minute;
                    if( second <= 9 ) second = '0' + second;
                    console.log(day, hour);
                    that.days = day;
                    that.hours = hour;
                    that.minutes = minute;
                    that.seconds = second;
                    initDiff--;
                    if(initDiff <= 0){
                        window.location.reload()
                    }
                }, 1000);
            },
            bidUrl(id) {
                self.location.href =  ctx + '/member-auction/auctionDetail?auctionId=' + id
            }
        },
        mounted: function () {
            var initDiff = parseInt(this.product.leftTime);
            console.log('time---', initDiff)
            this.timer(initDiff);
        }
    });

    // auction child element dom
    Vue.component('order-time-element', {
        props: [ 'order' ],
        template: '#order-time-item',
        data: function() {
            return {
                minutes: '0',
                seconds: '0'
            }
        },
        methods: {
            timer: function(initDiff){
                var that = this;
                window.setInterval(function () {
                    // 时间默认值
                    var minute = 0,
                        second = 0;
                    if(initDiff > 0) {
                        minute = Math.floor(initDiff/60);
                        second = Math.floor(initDiff%60);
                    }
                    if( minute <= 9 ) minute = '0' + minute;
                    if( second <= 9 ) second = '0' + second;
                    that.minutes = minute;
                    that.seconds = second;
                    initDiff--;
                    if(initDiff <= 0){
                        window.location.reload()
                    }
                }, 1000);
            }
        },
        mounted: function () {
            var initDiff = parseInt(this.order.restPaymentTime);
            this.timer(initDiff);
        }
    });

    // slide show --auction detail page
    Vue.component('slide-show', {
        props: ['show-array'],
        template: '#slide-show-template'
    });
    Vue.component('calculator', {
        props: ['label', 'multiple', 'defaultValue'],
        template: '#calculator-template',
        data: function() {
            return {
                value: this.defaultValue,
            }
        },
        mounted: function() {
            console.log('load--', this.defaultValue, 'this.value---', this.value);
        },
        methods: {
            increment: function () {
                // 类型转换
                var $value = parseFloat(this.value),
                    $multiple = parseFloat(this.multiple);
                this.value = $value + $multiple;
            },
            decrement: function () {
                // 类型转换
                var $value = parseFloat(this.value),
                    $multiple = parseFloat(this.multiple),
                    $defaultValue = parseFloat(this.defaultValue);

                if(($value - $multiple) <= $defaultValue) {
                    this.value = $defaultValue;
                } else {
                    this.value = $value - $multiple;
                }
            },
            inputChange: function (e) {
                console.log('input-change--', e.target.value)
            }
        }
    });
    Vue.component('z-pagenav', {
        template: `<nav class="zpagenav">` +
            `<ul class="page-ul">` +
            `<li v-bind:key="index" v-for="(item,index) in pageList" v-bind:class ="item.class" @click.stop="setPage(item)" v-html="item.html">` +
            `</li>` +
            `</ul>` +
            `</nav>`,
        props: {
            prevHtml: String,
            nextHtml: String,
            page: Number,
            total: Number,
            pageSize: Number,
            maxPage: Number
        },
        computed: {
            pageList: function() {
                var _this = this,
                    pageList = [];
                let pageCount = Math.ceil(_this.total / _this.pageSize);
                let page = _this.page;
                let prevHtml = _this.prevHtml ? _this.prevHtml : '<';
                let nextHtml = _this.nextHtml ? _this.nextHtml : '>';
                let maxPage = _this.maxPage ? _this.maxPage : 9;
     
                let hasPrev = page > 1;
                let hasNext = page < pageCount;
     
                //上一页
                pageList.push({
                    class: hasPrev ? '' : 'disabled',
                    page: hasPrev ? page - 1 : page,
                    html: prevHtml
                });
     
                //首页
                pageList.push({
                    class: page == 1 ? 'active' : '',
                    page: 1,
                    html: 1
                });
     
                var p0 = Math.floor(maxPage / 2);
                var p1 = 1 + 2 + p0; //首页+省略至少2个页码+中间页面数的一半
     
                var start, end;
                if(page >= p1) {
                    start = page - p0;
                    //前置省略号
                    pageList.push({
                        class: 'dot',
                        page: page,
                        html: '...'
                    });
                } else {
                    start = 2;
                }
     
                var p2 = page + p0;
                if(p2 < pageCount) {
                    end = p2;
                } else {
                    end = pageCount - 1;
                }
     
                //页码列表
                for(let i = start; i <= end; i++) {
                    pageList.push({
                        class: page == i ? 'active' : '',
                        page: i,
                        html: i
                    });
                }
     
                if(end < pageCount - 1) {
                    //后置省略号
                    pageList.push({
                        class: 'dot',
                        page: page,
                        html: '...'
                    });
                }
     
                //尾页
                if(pageCount > 1) {
                    pageList.push({
                        class: page == pageCount ? 'active' : '',
                        page: pageCount,
                        html: pageCount
                    });
                }
     
                //下一页
                pageList.push({
                    class: hasNext ? '' : 'disabled',
                    page: hasNext ? page + 1 : page,
                    html: nextHtml
                });
     
                return pageList;
            }
        },
        methods: {
            setPage: function(item) {
                if(item.class == '') {
                    this.$emit('pagehandler', item.page);
                }
            }
        }
    });

})(jQuery);