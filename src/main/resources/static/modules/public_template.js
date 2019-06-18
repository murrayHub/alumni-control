;(function($) {
    // 左边导航及logo
    Vue.component('navigation', { template: '#navigation-left' });
  
    // 页面标题
    Vue.component('title-page', {
        props: ['title'],
        template: '#title-name'
    });
    // 页面二级导航
    Vue.component('secondary-nav', {
        props: ['secondary-obj'],
        template: '#secondary-menu'
    });
    // 面包屑组件
    Vue.component('crumbs', {
        props: ['crumbs-obj'],
        template: '#crumbs-template',
    });
    // table 表格组件模版
    Vue.component('table-element', {
        props: ['data-resource', 'columns'],
        template: '#table-template',
        methods: {
            renderDom: function(rowData, columnData) {
                if(columnData.render) {
                    return columnData.render(rowData).dom;
                } else {
                    return rowData[columnData.dataIndex]
                }
            },
        },
        components: {
        }
    });
    // switch checkBox 单选筐组件
    Vue.component('switch-checkbox', {
        template: `
            <div class="newGreen ui toggle checkbox">
                <input type="checkbox" name="public" >
                <label></label>
            </div>
        `,
        mounted: function () {
            $('.newGreen.ui.toggle.checkbox').checkbox({
                onChecked: function () {
                    console.log('选中--');
                },
                onUnchecked: function () {
                    console.log('未选中--');
                }
            });
        },
        
    });
    Vue.component('ui-date', {
        template: '#ui-date',
        props: ['name','dateval'],
        mounted: function () {
           // 调用这个组件的方法 
             laydate.render({
                elem: '#time2',
                lang: 'en',
               type: 'datetime',
            });
        },
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