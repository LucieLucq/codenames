var _login = prompt( "What's your username for this demo ?" );

$.wesbmessenger.singleton()
  // Connect on the WorkESB server
  .connect( 'com.worbesb.demo', 'CHAT', {
    server: "ws://marcbuils.no-ip.org:80/"            
  } )
  // Bind talk on workesb to add
  .bind( 'talk', function( p_text ){
    $( '#conversation' ).prepend( $( '<li />' ).text( p_text ) );
  })
  .trigger( 'talk', '>>> ' + _login + ' is now connected' );
    
// Trigger a talk event on the WorkESB server when the user write a new line
$( '#send_button' ).click( function(){
  $.wesbmessenger.singleton().trigger( 'talk', _login + ': ' + $( '#send_text' ).val() );
});

