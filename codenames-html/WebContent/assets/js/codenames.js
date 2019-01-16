//Cacher/afficher des sections
$('ul a').bind('click', function() {
	
	//this -> lien cliqué
	let mySelecteurAAfficher = $(this).attr('href');
	
	$('section').hide();
	$(mySelecteurAAfficher).show();  //ou $($(this).attr('href')).show();
	
	return false;
	
});