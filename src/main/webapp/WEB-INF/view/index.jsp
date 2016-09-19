<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/jspf/taglibs.jsp" %>
<!DOCTYPE html>
<html class="no-js"> 
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>个人主页-周秋实</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="${ctx}/css/normalize.css">
        <link rel="stylesheet" href="${ctx}/css/font-awesome.css">
        <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/css/templatemo-style.css">
        <script src="${ctx}/js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
		
        <!-- SIDEBAR -->
        <div class="sidebar-menu hidden-xs hidden-sm">
            <div class="top-section">
                <div class="profile-image">
                    <img src="${ctx}/images/profile.jpg" alt="Volton">
                </div>
                <h3 class="profile-title">周秋实</h3>
                <p class="profile-description">java软件工程师</p>
            </div> <!-- top-section -->
            <div class="main-navigation">
                <ul class="navigation">
                    <li><a href="#top"><i class="fa fa-globe"></i>welcome</a></li>
                    <li><a href="#about"><i class="fa fa-pencil"></i>个人简介</a></li>
                    <li><a href="#projects"><i class="fa fa-paperclip"></i>项目案例</a></li>
                    <li><a href="#contact"><i class="fa fa-link"></i>请联系我</a></li>
                </ul>
            </div> <!-- .main-navigation -->
            <div class="social-icons">
                <ul>
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                    <li><a href="#"><i class="fa fa-rss"></i></a></li>
                </ul>
            </div> <!-- .social-icons -->
        </div> <!-- .sidebar-menu -->
        
        	
        <div class="banner-bg" id="top">
            <div class="banner-overlay"></div>
            <div class="welcome-text">
                <h2>网站建设 | 周秋实的主页</h2>
                <h5>这个网站是我个人的一个主页。里面有一些自己建设的网站，网站涉及多个领域。如果您对这些网站的源码感兴趣或者您想定制一个属于自己的网站，请联系我！！！</h5>
            </div>
        </div>

        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="fluid-container">

                <div class="content-wrapper">
                
                    <!-- ABOUT -->
                    <div class="page-section" id="about">
                    <div class="row">
                        <div class="col-md-12">
                            <h4 class="widget-title">个人简介</h4>
                            <div class="about-image">
                                <img src="${ctx}/images/8.jpg" alt="about me">
                            </div>
                            <p>拥有多年编程经验的java软件工程师。
                                                                             精通各类网站的建设，尤其是电子商务类型网站。
                               
                            </p>
                            <hr>
                        </div>
                    </div> <!-- #about -->
                    </div>
                    
                    <!-- PROJECTS -->
                    <div class="page-section" id="projects">
                    <div class="row">
                        <div class="col-md-12">
                            <h4 class="widget-title">项目案例</h4>
                            <p>以下是目前我提供的网站演示，如果您有需要，请联系我！</p>
                        </div>
                    </div>
                    <div class="row projects-holder">
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/1.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/2.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/3.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/4.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Quisque mattis sit amet dolor eu scelerisque. Vivamus bibendum massa et nisl tempus commodo.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/5.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Quisque mattis sit amet dolor eu scelerisque. Vivamus bibendum massa et nisl tempus commodo.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="project-item">
                                <img src="${ctx}/images/6.jpg" alt="">
                                <div class="project-hover">
                                    <div class="inside">
                                        <h5><a href="#">Pellentesque porta ligula</a></h5>
                                        <p>Quisque mattis sit amet dolor eu scelerisque. Vivamus bibendum massa et nisl tempus commodo.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!-- .projects-holder -->
                    </div>
                    <hr>

                    <!-- CONTACT -->
                    <div class="page-section" id="contact">
                    <div class="row">
                        <div class="col-md-12">
                            <h4 class="widget-title">请联系我</h4>
                            <p>
                               	如果您想定制网站或者想进一步了解一些网站，请填写下面的联系方式，我会给您答复！
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <form action="#" method="post" class="contact-form">
                            <fieldset class="col-md-4 col-sm-6">
                                <input type="text" id="your-name" placeholder="您的姓名">
                            </fieldset>
                            <fieldset class="col-md-4 col-sm-6">
                                <input type="email" id="email" placeholder="您的电子邮箱">
                            </fieldset>
                            <fieldset class="col-md-4 col-sm-12">
                                <input type="text" id="your-subject" placeholder="您的电话号码">
                            </fieldset>
                            <fieldset class="col-md-12 col-sm-12">
                                <textarea name="message" id="message" cols="30" rows="6" placeholder="写下您的想法"></textarea>
                            </fieldset>
                            <fieldset class="col-md-12 col-sm-12">
                                <input type="submit" class="button big default" value="提交">
                            </fieldset>
                        </form>
                    </div> <!-- .contact-form -->
                    </div>
                    <hr>

                    <div class="row" id="footer">
                        <div class="col-md-12 text-center">
                            <p class="copyright-text">Copyright &copy; qiushi.zhou</p>
                        </div>
                    </div>

                </div>

            </div>
        </div>

        <script src="${ctx}/js/jquery-1.10.2.min.js"></script>
        <script src="${ctx}/js/plugins.js"></script>
        <script src="${ctx}/js/main.min.js"></script>

    </body>
</html>