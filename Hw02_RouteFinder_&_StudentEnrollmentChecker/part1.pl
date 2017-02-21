
flight(istanbul,ankara).
flight(ankara,istanbul).

flight(istanbul,trabzon).
flight(trabzon,istanbul).

flight(istanbul,kars).
flight(kars,istanbul).

flight(istanbul,gaziantep).
flight(gaziantep,istanbul).

flight(istanbul,konya).
flight(konya,istanbul).

flight(istanbul,antalya).
flight(antalya,istanbul).

flight(istanbul,izmir).
flight(izmir,istanbul).

flight(ankara,trabzon).
flight(trabzon,ankara).

flight(ankara,kars).
flight(kars,ankara).

flight(ankara,konya).
flight(konya,ankara).

flight(ankara,izmir).
flight(izmir,ankara).

flight(edirne,edremit).
flight(edremit,edirne).

flight(erzincan,edremit).
flight(edremit,erzincan).

 
 uniq(Data,Uniques) :- sort(Data,Uniques).

 rout(X,Y,List):- flight(X,Y),\+(member(Y,List)).  % \+ is 'not', it compiles on gprolog
 rout(X,Y) :-  flight(X,Y).
 rout(X,Y) :-  flight(X,Z),rout(Z,Y,[X,Z]).
 rout(X,Y) :-  flight(X,Z),rout(Z,M,[X,Z]),rout(M,Y,[X,Z,M]).
 
 route(X,Y) :-
			findall(L,rout(X,L),Z),
			uniq(Z,Y).

% This code prints some cities more than once (not infinite loop !!!),
% thats because evry city is connected to each other in both direction.



	
	
	

