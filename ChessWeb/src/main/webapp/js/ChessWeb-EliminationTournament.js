/*
*/

//
//  This convention can be used to extend existing
//    javasript objects to include functions
//    which are used frequently ...
String.prototype.reverse = function(){
	splitext = this.split("");
	revertext = splitext.reverse();
	reversed = revertext.join("");
	return reversed;
}

String.prototype.startsWith = function(prefix){
	return this.indexOf(prefix)===0;
}

String.prototype.endsWith = function(suffix) {
    return this.match(suffix+"$") == suffix;
};


//
//  Tournament Table ...
//    HTML creates tables in a particular manner making the 
//    tournament table design complex to create and maintain...
function createEliminationTournamentTable(games, singleElimination){
	 var PLAYER_POSITION = "$$_p_$$";
	 var LOSE_POSITION = "$$_l_$$";
	 var WIN_POSITION = "$$_w_$$";
	 
	 var table = [];
	 var rowSpan = [];
	
	 var playerCount = 0;
	 for (var i=0; i< games.length; i++){
		 var game = games[i].value;
		 if (game.id.startsWith("P")){
			 playerCount += 2;
		 }
	 }
	 
	 var i = 0;
	 var spanCount = 0;
	 while (i<playerCount){
	    if (0==i){
	       table[i] = "<td rowspan='1' class='player'>"+PLAYER_POSITION+"</td>";
	       rowSpan[i] = 1;
	       i+=1;
	    }else{
	       var iTmp = i-1;
	       for (var j=0; j<iTmp; j++){
	          table[i] = table[j];
	          rowSpan[i] = rowSpan[j]*2;
	          i+=1;
	       }
	       rowSpan[i] = rowSpan[j]*2;
	       if (singleElimination){
	           table[i] = table[j]+"<td rowspan='"+rowSpan[i]+"' class='player'>"+WIN_POSITION+"</td>";
	       }else{
	           table[i] = "<td rowspan='"+rowSpan[i]+"' class='player'>"+LOSE_POSITION+"</td>"+table[j]+"<td rowspan='"+rowSpan[i]+"' class='player'>"+WIN_POSITION+"</td>";
	       }
	       i+=1;
	    }
	 }
	 
	 var line = "";
	 var tmpLine;
	 var playerNumber = 1;
	 var counts = [1,1,1,1,1,1,1,1,1,1]; // 20 columns 2^10 players

	 for (var i=0; i<table.length; i++){
		tmpLine = table[i];
		var tmpIndex = 0;
		
		while(0<tmpLine.indexOf(WIN_POSITION)){
			tmpLine = tmpLine.replace(WIN_POSITION, "<div id='W"+getId(tmpIndex, counts[tmpIndex])+"' onclick='openGame(\"W"+getId(tmpIndex, counts[tmpIndex])+"\")' style=\"cursor:pointer;\"></div>");
			tmpLine = tmpLine.reverse();
			var revString = "<div id='C"+getId(tmpIndex, counts[tmpIndex])+"' onclick='openGame(\"C"+getId(tmpIndex, counts[tmpIndex])+"\")' style=\"cursor:pointer;\"></div>";
			tmpLine = tmpLine.replace(LOSE_POSITION, revString.reverse());
			tmpLine = tmpLine.reverse();
			counts[tmpIndex] = counts[tmpIndex]+1;
			tmpIndex ++;
		}
		
		tmpLine = tmpLine.replace(PLAYER_POSITION, "<div id='P"+playerNumber+"' onclick='openGame(\"P"+playerNumber+"\")' style=\"cursor:pointer;\")></div>");
		playerNumber++;
	    line = "<tr>"+tmpLine+"</tr>" + line;
	 }
	 
	 line = "<table border='1' cellpadding='8px' style='border: 1px; border-spacing: 0px; border-color: #808080'>"+line+"</table>";
	 
	 // return the table -
	 return line;
}

function openGame(gameId){
	if (!gameId.startsWith("P")){
		gameId = gameId.substring(0, gameId.length-2);
	}else{
		var num = gameId.substring(1);
		if (0 == num%2){
			num--;
		}
		gameId = "P"+num;
	}
		
	$("#watchingGameId").html(gameId);
	$('#dlgWatchGame').dialog('open');
	//joinGame(gameId);
	return;
}

function getId(tmpIndex, num){
	var gamePart =  Math.ceil(num/2);
	if (gamePart==0){
		gamePart = 1;
	}
	
	return (tmpIndex+1) +"_"+ gamePart +"_"+ (((num+1)%2)+1);
}

function log2(num){
	for(var i=0; num>1; i++) num /= 2;
	return i;
}

//
//  This method populated the Tournament result table 
//    with results based on the game IDs.
function populateTournament(games){
	var playerNumber = 1;
	
	for (var i=0; i<games.length; i++){
		var game = games[i].value;
		
		// primary game
		if (game.id.startsWith("P")){
			var gameId = game.id.substring(1);
			
			var player1 = "#P"+(gameId*2);
			var player2 = "#P"+((gameId*2)-1);
			
			$(player1).html(game.player1);
			$(player2).html(game.player2);
		
		//  consolation game
		}else if (game.id.startsWith("C")){
			var player1 = "#"+game.id+"_1";
			var player2 = "#"+game.id+"_2";
			
			if(game.player1!=""){
				$(player1).html(game.player1);
			}
			if(game.player2!=""){
				$(player2).html(game.player2);
			}
			
		//  winner game
		}else if (game.id.startsWith("W")){
			var player1 = "#"+game.id+"_1";
			var player2 = "#"+game.id+"_2";

			if(game.player1!=""){
				$(player1).html(game.player1);
			}
			if(game.player2!=""){
				$(player2).html(game.player2);
			}
		}else{
			alert(game.id);
		}
	}
	
	return;
}

function redrawTournament(){
	
	var strUrl = "rest/chess/redrawTournament/idOne/"; 
	$.ajax({
		async: true,
		url: strUrl,
		success: function(tournament){
				var tournamentTable = createEliminationTournamentTable(tournament.games.entry);
				$('#divTournament').html(tournamentTable);
				populateTournament(tournament.games.entry);
	            setRedrawTimeout();
	       },
	    error: function(xhr,err,e){ 
	             setRedrawTimeout();
	           }
	     });
	
	return;
}

var redrawTournamentTimeout;
var tournamnetTimeoutValue = 3000;
function setRedrawTimeout(){
	
	redrawTournamentTimeout = setTimeout("redrawTournament()", tournamnetTimeoutValue);
	
	return;
}
