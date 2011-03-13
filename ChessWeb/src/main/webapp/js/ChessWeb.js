
//  variables global to page
var alpha = ["A", "B", "C", "D", "E", "F", "G", "H"];
var moving = false;
var currentGame;
var redrawGameTimeout;
var gameTimeoutValue = 5000; // in seconds

// functions
//
//  translate the text string from the reset call to the 
//    corresponding image of a chess piece 
function getChessPiece(pieceName){
	if ("W:Pawn"===pieceName)
		return "<img id='chessPiece' src='./images/whitePawn.png' style='height: 60px; width: 60px;' />";
	if ("W:Rook"===pieceName)
		return "<img id='chessPiece' src='./images/whiteRook.png' style='height: 60px; width: 60px;' />";
	if ("W:Knight"===pieceName)
		return "<img id='chessPiece' src='./images/whiteKnight.png' style='height: 60px; width: 60px;' />"; 
	if ("W:Bishop"===pieceName)
		return "<img id='chessPiece' src='./images/whiteBishop.png' style='height: 60px; width: 60px;' />";
	if ("W:King"===pieceName)
		return "<img id='chessPiece' src='./images/whiteKing.png' style='height: 60px; width: 60px;' />";
	if ("W:Queen"===pieceName)
		return "<img id='chessPiece' src='./images/whiteQueen.png' style='height: 60px; width: 60px;' />";
	if ("B:Pawn"===pieceName)
		return "<img id='chessPiece' src='./images/blackPawn.png' style='height: 60px; width: 60px;' />";
	if ("B:Rook"===pieceName)
		return "<img id='chessPiece' src='./images/blackRook.png' style='height: 60px; width: 60px;' />";
	if ("B:Knight"===pieceName)
		return "<img id='chessPiece' src='./images/blackKnight.png' style='height: 60px; width: 60px;' />";
	if ("B:Bishop"===pieceName)
		return "<img id='chessPiece' src='./images/blackBishop.png' style='height: 60px; width: 60px;' />";
	if ("B:King"===pieceName)
	 	return "<img id='chessPiece' src='./images/blackKing.png' style='height: 60px; width: 60px;' />";
	if ("B:Queen"===pieceName)
		return "<img id='chessPiece' src='./images/blackQueen.png' style='height: 60px; width: 60px;' />";
   return "";
}

//  
//  set the board based on the data returned from the server.
function setBoard(board){
	//  if moving don't draw the board.
	if (moving) return;
	
	//  split the rows apart ...
	var rows = board.split("*");
	for (var y=0; y<rows.length; y++){
		// split the columns apart ...
		var columns = rows[y].split("|");
		for (var x=0; x<columns.length; x++){
			var theId = "td[id='"+alpha[x]+":"+y+"']";
			
			//  place the chess piece on the board ... 
			$(theId).html(getChessPiece(columns[x]));
		}
	}
 
	//  make all chess pieces images draggable ...
	$("img[id='chessPiece']").draggable({
		grid: [64,64], 
		cursor: 'pointer', 
		revert:'invalid',
		start: function(event, ui){
				moving=true;
				clearRedrawGameTimeout();
				$(this).css('z-index', '1000');
				$("#origin").val($( this ).parent().attr('id'));
			},
		stop: function(event, ui){
				moving = false;
				$(this).css('z-index', '1');
			}
	});

 
	//  make all td elements droppable ...
	$("td[id]").droppable({
		accept: "#chessPiece",
		drop: function( event, ui ) {
				$("#destination").val($( this ).attr('id'));
				if ($("#origin").val()!=$("#destination").val){
					sendMove();
				}
			}
	});
	     
	return;
}

//  create a new chess game ...
function createGame(gameId){
	currentGame = gameId;
    $("#gameId").html(currentGame);
    var strUrl = "rest/chess/createGame/" + currentGame + "/"; 
	asyncGameMessage(strUrl);
	$("#origin").val("");
	$("#destination").val("");
}

//  join a chess game ...
function joinGame(gameId){
	currentGame = gameId;
	$("#gameId").html(currentGame);
            
	var strUrl = "rest/chess/redrawGame/" + currentGame + "/"; 
	asyncGameMessage(strUrl);
  
	$("#origin").val("");
	$("#destination").val("");
}
  
//  
// note this function polling the server ...
function redrawGame(){
	
	//  redraw the current game ...
	var strUrl = "rest/chess/redrawGame/" + currentGame + "/"; 
	$.ajax({
		async: true,
		url: strUrl,
		success: function(board){
				setBoard(board);
				setRedrawTimeout();
	       },
	    error: function(xhr,err,e){ 
	         $("#movesStatus").html("asyncGameMessage("+strUrl+") error : " + e + "  " + err); 
	             setRedrawTimeout();
	           }
	     });
	
	//  update the active player field ...
	strUrl = "rest/chess/activePlayer/" + currentGame + "/";
	$.ajax({
		async: true,
		url: strUrl,
		success: function(player){
				$("#activePlayer").html(player);
	       }
	});
	
	return;
}

//  this method sets the time out 
//    for the redraw function ...
function setRedrawTimeout(){
	redrawGameTimeout = setTimeout("redrawGame()", gameTimeoutValue);
	
	return;
}

//  This method clears (cancels) 
//    the redraw time out ...
function clearRedrawGameTimeout(){
	clearTimeout(redrawGameTimeout);
    redrawGameTimeout = null;
    
    return;
}

//
//  This method sends the move generated by the 
//    dragging and dropping of the chess piece
//    to the server for validation ...
function sendMove(){
	if ($("#origin").val() != $("#destination").val() ){
		var strUrl = "rest/chess/makeMove/" + currentGame + "/" + $("#origin").val() + "-" + $("#destination").val() + "/"; 
		asyncGameMessage(strUrl);
	}
	$("#origin").val("");
	$("#destination").val("");
      
	return;
}


function syncGameMessage(strUrl){
	// synchronous ajax call ...
	var returnData = $.ajax({
         async: false, 
         url: strUrl,
         success: function(board){ },
         error: function(xhr,err,e){ $("#movesStatus").html("syncGameMessage("+strUrl+") error : " + e + "  " + err); }
       }).responseText;
      
        
	return returnData;
}
  
function asyncGameMessage(strUrl){
	// asynchronous ajax call ...
	$.ajax({
         url: strUrl,
         success: function(board){
             redrawGame();
          },
          error: function(xhr,err,e){ 
        	 $("#movesStatus").html("asyncGameMessage("+strUrl+") error : " + e + "  " + err); 
          }
        });
      
	return;
}

//
//  This method creates the HTML representation of the 
//    chess board ...
function drawBoard(){
	var board = "<table cellspacing=\"0\" cellpadding=\"0\">";
	var color = "black";
	var row = 8;
    board += "<tr><td class=\"boardLabel\">&nbsp;</td><td class=\"boardLabel\">A</td><td class=\"boardLabel\">B</td><td class=\"boardLabel\">C</td><td class=\"boardLabel\">B</td><td class=\"boardLabel\">E</td><td class=\"boardLabel\">F</td><td class=\"boardLabel\">G</td><td class=\"boardLabel\">H</td><td class=\"boardLabel\">&nbsp;</td></tr>";
	for (var y=0; y<8; y++){
		board += "<tr><td class=\"boardLabel\">&nbsp;"+row+"&nbsp;</td>";
		for (var x=0; x<8; x++){
			board += "<td id=\"" + alpha[x] + ":" + y + "\" class=\"" + color +"\"> &nbsp; </td>";
			if ("black"==color){
				color = "red";
			}else{
				color = "black";
			}
		}
		if ("black"==color){
			color = "red";
		}else{
			color = "black";
		}
		board += "<td class=\"boardLabel\">&nbsp;"+row+"&nbsp;</td></tr>";
		row--;
	}
    board += "<tr><td class=\"boardLabel\">&nbsp;</td><td class=\"boardLabel\">A</td><td class=\"boardLabel\">B</td><td class=\"boardLabel\">C</td><td class=\"boardLabel\">B</td><td class=\"boardLabel\">E</td><td class=\"boardLabel\">F</td><td class=\"boardLabel\">G</td><td class=\"boardLabel\">H</td><td class=\"boardLabel\">&nbsp;</td></tr>";
	board += "</table>";
	$("#boardPlacement").html(board);
      
    return;
}            

  
  