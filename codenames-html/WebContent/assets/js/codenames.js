//Cacher/afficher des sections
$('nav a').bind('click', function() {
	
	//this -> lien cliqué
	let mySelecteurAAfficher = $(this).attr('href');
	
	$('section').hide();
	$(mySelecteurAAfficher).show();  //ou $($(this).attr('href')).show();
	
	return false;
	
});