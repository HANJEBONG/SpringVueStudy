<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.page-link:hover{
	cursor: pointer;
}
.post-author{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
.post-date{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>Archive Page</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"></li>
                            <li class="breadcrumb-item active" aria-current="page"> </li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" id="listApp">
        <div class="container">
            <div class="row">

                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4" v-for="vo in food_list">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.5s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                           <a :href="'../food/detail_before.do?fno='+vo.fno">
                            <img :src="'http://www.menupan.com'+vo.poster" alt="">
                           </a>
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#"></a>
                                    </div>
                                    <!-- Post Date -->
                                    <div class="post-date">
                                        <a href="#"></a>
                                    </div>
                                </div>
                                <!-- Post Comment & Share Area -->
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                    </div>
                                    <!-- Post Comments -->
                                    <div class="post-comments">
                                        <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                    </div>
                                    <!-- Post Share -->
                                    <div class="post-share">
                                        <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <h4 class="post-headline">{{vo.name}}</h4>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            	<li class="page-item" v-if="startpage>1">
                                    <a class="page-link" @click="prev"><i class="fa fa-angle-double-left" aria-hidden="true"></i> prev</a>
                                </li>
                                <li :class="i===curpage?'page-item active':'page-item'" v-for="i in range(startpage,endpage)">
                                    <a class="page-link" @click="pagemove(i)">{{i}} <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="page-item" v-if="endpage<totalpage">
                                    <a class="page-link" @click="next">Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>{{curpage}} page / {{totalpage}} pages</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <script>
   		let foodApp=Vue.createApp({
   			data(){
   				return{
   					food_list:[],
   					curpage:1,
   					totalpage:0,
   					startpage:0,
   					endpage:0
   				}
   			},
   			mounted(){
   				this.dataRecv()
   			},
   			methods:{
   				dataRecv(){
   					axios.get('../food/list_vue.do',{
   						params:{
   							page:this.curpage
   						}
   					}).then(response=>{
   						console.log(response)
   						this.food_list=response.data.list
   						this.curpage=response.data.curpage
   						this.totalpage=response.data.totalpage
   						this.startpage=response.data.startpage
   						this.endpage=response.data.endpage
   					})
   				},
   				range(start,end){
   					let arr=[];
   					let len=end-start
   					for(let i=0;i<=len;i++){
   						arr[i]=start+i
   					}
   					
   					return arr
   				},
   				prev(){
   					this.curpage=this.startpage-1
   					this.dataRecv()
   				},
   				next(){
   					this.curpage=this.endpage+1
   					this.dataRecv()
   				},
   				pagemove(page){
   					this.curpage=page
   					this.dataRecv()
   				}
   			}
   			
   			
   		}).mount('#listApp')
    </script>
</body>
</html>