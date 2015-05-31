<%--
	기능 : 공통 좌측 메뉴	
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jbernate.cm.util.*"%>

	<section id="section-menu" ng-controller="leftMenuController">
		
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="well" style="height: 665px;">
						<div>
							<ul class="nav">
								<li ng-show="conIsAdmin">
<!-- 								<label label-default="" class="tree-toggle nav-header"><span class="fa fa-medkit"></span> Master</label> -->
								<label label-default="" class="tree-toggle nav-header"><span class="fa fa-sitemap"></span> Master</label>
									<ul class="nav tree">
										<li>
<!-- 											<a href="#Team" ng-class="getLeftMenuClass('/Team')"><span class="fa fa-university"></span> 팀관리</a> -->
											<a href="#Team" ng-class="getLeftMenuClass('/Team')"><span class="fa fa-users"></span> 팀관리</a>
										</li>
										<li>
											<a href="#User" ng-class="getLeftMenuClass('/User')"><span class="fa fa-user"></span> 사용자관리</a>
										</li>
										<li>
<!-- 											<a href="#"><span class="fa fa-users"></span> 제품그룹관리</a> -->
											<a href="#GrpPrd" ng-class="getLeftMenuClass('/User')"><span class="fa fa-cubes"></span> 제품그룹관리</a>
										</li>
										<li>
<!-- 											<a href="#"><span class="fa fa-heartbeat"></span> 제품관리</a> -->
											<a href="#Prd" ng-class="getLeftMenuClass('/User')"><span class="fa fa-eyedropper"></span> 제품관리</a>
										</li>
										<li>
<!-- 											<a href="#"><span class="fa fa-bed"></span> 병원관리</a> -->
											<a href="#Hopt" ng-class="getLeftMenuClass('/User')"><span class="fa fa-hospital-o"></span> 병원관리</a>
										</li>
									</ul>
								</li>
								<li ng-show="conIsAdmin">
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
								<label label-default="" class="tree-toggle nav-header"><span class="glyphicon glyphicon-usd"></span> Result</label>
									<ul class="nav tree">
										<li>
											<a href="#"><span class="fa fa-street-view"></span> 개인별조회</a>
										</li>
										<li ng-show="conIsAdmin">
											<a href="#"><span class="fa fa-sort-amount-desc"></span> 팀별조회</a>
										</li>																				
										<li ng-show="conIsAdmin">
<!-- 											<a href="#"><span class="fa fa-toggle-off"></span> 변경이력조회</a> -->
											<a href="#"><span class="fa fa-toggle-on"></span> 변경이력조회</a>
										</li>																				
									</ul>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<section id="section-content">