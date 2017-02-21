add([],0).
add([Head|Tail],X) :- add(Tail,Y), X is Head + Y.