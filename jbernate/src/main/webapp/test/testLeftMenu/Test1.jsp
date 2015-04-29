<!DOCTYPE html>

<%@page import="com.jbernate.cm.util.ChkUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>	
		<title>.</title>
		
		<script type="text/javascript" src="../../js/resource/jquery-2.1.3.min.js"></script>
	    <script type="text/javascript" src="../../js/resource/angular-1.2.17.js"></script>
	    <script type="text/javascript" src="../../js/resource/bootstrap/ui-bootstrap-tpls-0.12.1.js"></script>
	    <script type="text/javascript" src="../../js/resource/bootstrap/bootstrap.min.js"></script>
	    <script type="text/javascript" src="../../js/resource/ng-grid.debug.js"></script>
	    <script type="text/javascript" src="../../js/app.js"></script>
	    
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/bootstrap/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="../../css/ng-grid.css" />
	    
	    <link rel="stylesheet" type="text/css" media="screen" href="../../css/common.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/layout.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/font-awesome-4.3.0/css/font-awesome.min.css" />
		
		<style>
			/* CSS used here will be applied after bootstrap.css */
			.panel a {
			  color:#777;
			}
			
			.panel-scroll {
			    	max-height: 320px;
				overflow: hidden;
				overflow-y: auto;
			  }
			
			.panel a:hover {
			 text-decoration:none;
			 color:#222;
			}
			
			.panel .table td {
			 border-color: #f3f3f3;
			}
		</style>
		
	</head> 
	<body>
		<hr>
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
			
			
			            <div id="push"></div>
			        
	</body>
</html>