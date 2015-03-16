 $(document).ready(function() {

                $("ul#topnav li").hover(function() { //Hover over event on list item
                    $(this).addClass('navhovercolor');
                    $(this).find("span").show();
                } , function() {
                    $(this).removeClass('navhovercolor');
                    $(this).find("span").hide();
                });

            });


