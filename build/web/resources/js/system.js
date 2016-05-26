$(document).ready(function(){
    
    $('.shape').shape();
    
    $("div[data-event='scroll']").click(function(event){
        
        var idToScroll = $(this).attr('href');
        var target = $(idToScroll);
        
         //Scroll
         $('html, body').animate({scrollTop:target.offset().top}, 1000);
     });
     
     $("div[data-animation-flip]").on('click',function(event){
        
        var flipCommand = $(this).attr('data-animation-flip');
        
        $('.shape').shape('flip '+flipCommand);
        
     });
     
    $('#popup').popup();
    
});