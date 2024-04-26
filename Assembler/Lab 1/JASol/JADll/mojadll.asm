;-------------------------------------------------------------------------
;.586
;INCLUDE C:\masm32\include\windows.inc 

.CODE
;-------------------------------------------------------------------------
; To jest przyk³adowa funkcja. 
;-------------------------------------------------------------------------
;parametry funkcji: RCX RDX R8 R9 stos, 
;lub zmiennoprzec.  XMM0 1 2 3

MyProc2 proc	
xor		rax, rax
add 	rcx, rdx
sub		rdx, rcx
mov 	rax, rcx
std
ror	rcx,1
mul 	rcx
cld
ret

MyProc2 endp



END 			;no entry point
;-------------------------------------------------------------------------
