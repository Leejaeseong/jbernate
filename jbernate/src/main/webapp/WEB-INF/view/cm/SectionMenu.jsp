<%--
	기능 : 공통 좌측 메뉴	
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jbernate.cm.util.*"%>

	<section id="section-menu" class="east-back">
		
		<!-- 작업 중 -->
		<!-- http://www.bootply.com/zwcoG0xEfZ -->
		<!-- http://www.bootply.com/iLCBse1Ze4 -->
		
		<!--  
		<ul><br/>
			<li>공통정보</li>
			<li>&nbsp;&nbsp;&nbsp;통합코드등록</li>		
			<li>&nbsp;&nbsp;&nbsp;우편번호관리</li>		
			<li>&nbsp;&nbsp;&nbsp;세금정보관리</li>		
			<li>커뮤니티관리</li>		
			<li>&nbsp;&nbsp;&nbsp;공지사항</li>		
			<li>&nbsp;&nbsp;&nbsp;질문&답변</li>		
			<li>시스템메뉴정보</li>		
			<li>&nbsp;&nbsp;&nbsp;즐겨찾기조회</li>		
			<li>&nbsp;&nbsp;&nbsp;메인메뉴선택</li>
		</ul>
		-->

		<!-- 
		<div class="container">
		    <div class="row">
		        <div class="col-sm-3">
		          <div class="panel panel-group" id="main">
		                      <div class="panel-body">
		                      <div class="panel-heading">  
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" data-parent="#main" href="#accordion"><span class="glyphicon glyphicon-folder-close"></span> Make Selection</a>
		                        </h4>  
		                        </div>
		            <div class="panel-collapse collapse panel-scroll" id="accordion">
		                <div class="panel panel-default">
		                    <div class="panel-heading">
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-folder-close"></span> Home</a>
		                        </h4>
		                    </div>
		                    <div id="collapseOne" class="panel-collapse collapse">
		                        <div class="panel-body">
		                            <table class="table">
		                                <tbody><tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-pencil text-primary"></span> Articles</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-flash text-success"></span> News</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-file text-info"></span> About</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-comment text-success"></span> Comments</a> <span class="badge">42</span>
		                                    </td>
		                                </tr>
		                            </tbody></table>
		                        </div>
		                    </div>
		                </div>
		                <div class="panel panel-default">
		                    <div class="panel-heading">
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-user"></span> Profile</a>
		                        </h4>
		                    </div>
		                    <div id="collapseThree" class="panel-collapse collapse ">
		                        <div class="panel-body">
		                            <table class="table">
		                                <tbody><tr>
		                                    <td>
		                                        <a href="#">Change Password</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#">Notifications</a> <span class="label label-info">9</span>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#">Import</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#" class="text-danger"><span class="glyphicon glyphicon-trash text-danger"></span> Delete Account</a>
		                                    </td>
		                                </tr>
		                            </tbody></table>
		                        </div>
		                    </div>
		                </div>
		                <div class="panel panel-default">
		                    <div class="panel-heading">
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-th"></span> Examples</a>
		                        </h4>
		                    </div>
		                    <div id="collapseTwo" class="panel-collapse collapse ">
		                        <div class="panel-body">
		                            <table class="table">
		                                <tbody><tr>
		                                    <td>
		                                        <a href="#">Orders</a> <span class="label label-success">$ 42</span>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#">Invoices</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#">Shipments</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#">Logging</a>
		                                    </td>
		                                </tr>
		                            </tbody></table>
		                        </div>
		                    </div>
		                </div>
		                <div class="panel panel-default">
		                    <div class="panel-heading">
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
									<span class="glyphicon glyphicon-file"></span> Reports</a>
		                        </h4>
		                    </div>
		                    <div id="collapseFour" class="panel-collapse collapse">
		                        <div class="panel-body">
		                            <table class="table">
		                                <tbody><tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-usd"></span> Sales</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-user"></span> Customers</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-tasks"></span> Products</a>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>
		                                        <a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</a>
		                                    </td>
		                                </tr>
		                            </tbody></table>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		
		                              </div>
		           
		
		    </div>
		</div>
		</div>
		 -->
		 
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="well">
						<div>
							<ul class="nav">
								<li>
								<label label-default="" class="tree-toggle nav-header"><span class="fa fa-medkit"></span> Master</label>
									<ul class="nav tree">
										<li>
											<a href="#Test1"><span class="fa fa-university"></span> 팀관리</a>
										</li>
										<li>
											<a href="#Test2"><span class="fa fa-user"></span> 직원관리</a>
										</li>
										<li>
											<a href="#"><span class="fa fa-users"></span> 제품그룹관리</a>
										</li>
										<li>
											<a href="#"><span class="fa fa-heartbeat"></span> 제품관리</a>
										</li>
									</ul>
								</li>
								<li>
								<label label-default="" class="tree-toggle nav-header"><span class="fa fa-folder-open"></span> Basic</label>
									<ul class="nav tree">
										<li>
											<a href="#"><span class="fa fa-rss"></span> RATE관리</a>
										</li>
										<li>
											<a href="#"><span class="fa fa-line-chart"></span> 목표관리</a>
										</li>
										<li>
											<a href="#"><span class="fa fa-pie-chart"></span> 실적관리</a>
										</li>										
									</ul>
								</li>               
								<label label-default="" class="tree-toggle nav-header"><span class="glyphicon glyphicon-usd"></span> Incentive</label>
									<ul class="nav tree">
										<li>
											<a href="#"><span class="fa fa-street-view"></span> 개인별조회</a>
										</li>
										<li>
											<a href="#"><span class="fa fa-sort-amount-desc"></span> 팀별조회</a>
										</li>																				
									</ul>
								</li>
								<label label-default="" class="tree-toggle nav-header">Common</label>
									<ul class="nav tree">
										<li>
											<a href="#"><span class="fa fa-toggle-off"></span> 변경이력조회</a>
										</li>																				
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<section id="section-content">