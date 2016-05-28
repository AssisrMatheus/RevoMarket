$(document).ready(function(){
    
    //Aciona o scroll do clique
    $("div[data-event='scroll']").click(function(event){
        
        var idToScroll = $(this).attr('href');
        var target = $(idToScroll);
        
         //Scroll
         $('html, body').animate({scrollTop:target.offset().top}, 1000);
     });
     
     //Aciona o popup ao dar hover no mouse
    $('#popup').popup();
    
    //Aciona o efeito de collapse (pesquisa de acoes por exemplo)
    $('.ui.accordion').accordion();
});