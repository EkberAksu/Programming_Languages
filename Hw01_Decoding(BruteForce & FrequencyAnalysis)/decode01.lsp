; *********************************************
; *  341  Programming Languages               *
; *  Fall 2016                                *
; *            Homework01                     *
; *           ID:131044086                    *
; *      Author: Aikeboer Aizezi              *
; *********************************************

;; ENVIRONMENT
;; "c2i, "i2c",and "apply-list"
(load "include.cl")

;; test document
(load "document.cl")

;; test-dictionary
;; this is needed for spell checking
(load "test-dictionary.cl")

;; (load "dictionary.cl") ;;  real dictionary (45K words)

;*************************************************************
;*                     Functions                              *
;**************************************************************

;encodes the decoded file, and returns the mapped array
(defun Code-Breaker (document decoder-function)
   (funcall decoder-function  document)
)

(defun Gen-Decoder-B-0 (document)
	(setq  alpha-arr (make-array '(26 2) :initial-contents
		'((a _)(b _)(c _)(d _)(e _)(f _)(g _)(h _)(i _)
			(j _)(k _)(l _)(m _)(n _)(o _)(p _)(q _)(r _)
			(s _)(t _)(u _)(v _)(w _)(x _)(y _)(z _))))
	(maparr '(e t a o i n)  alpha-arr)
	(brute-force  alpha-arr (sort-the-list (doc-to-list document)))
	(write alpha-arr))
	
;This function takes in an element and a list and returns the index of that element in that list
(defun indexoflist (i list) 
  (if (null list)
   -1
    (if (eq (car list) i)
      0
      (if (= (indexoflist i (cdr list)) -1) 
       -1
       (+ 1 (indexoflist i (cdr list)))))))

(write (indexoflist 4 '(1 2 3 4 5)))
(terpri)	   

(write *test-document*)
(terpri)

;transders the document into single list form
(defun doc-to-list (p) 
	(let (lst)
		(cond
			((null p) nil)
			(t (dolist (x p)
				(dolist (y x)
					(push y lst)))))
		lst)) ;returns lst

(write (doc-to-list *test-document*))
(terpri)

;sorts the given list according to items length
(defun sort-the-list (lst)
  (loop repeat (1- (length lst)) do
    (loop for ls on lst while (rest ls) do
      (when (< (length (first ls)) (length (second ls)))
        (rotatef (first ls) (second ls)))))
  lst)
  

#|(defun sort-the-list (lst)
	(cl-sort lst #'> :key #'length)
	lst)|#

(write (sort-the-list (doc-to-list *test-document*)	))
(terpri)

;makes a list of the words from the document, of which the length is the same with the given word
(defun mk-word-list (word dictionary)
	(let ((lst) (len (length word)))
		(format t "len of word is: ~a~%" len)
		;(setf len (length word) )
		(dolist (x dictionary)
			;(format t "the word: ~a~%" x)
			(if (= (length x) len)
			(push x lst)))
		lst))

		
(write (mk-word-list "loool" *dictionary*))
(terpri)
;(format t "~a can be ~a~%" "strings" "formatted")

(defun is-full (arr)
	(write arr))
(defun map-arr (word arr)
	(write arr)
	(write word))
	
;decodes the encoded document using brute force method
(defun brute-force (arr doc)
	(if (is-full arr) t
		(let ((word-lst (mk-word-list (car doc) *dictionary*)))
			(dolist (x word-list)
				(if (map-arr x arr)
					(if (brute-force arr (cdr doc)) 
						(return-from brute-force t)))))))
						

(defun Gen-Decoder-A (document)
	(setq  alpha-arr (make-array '(26 2) :initial-contents
		'((a _)(b _)(c _)(d _)(e _)(f _)(g _)(h _)(i _)
			(j _)(k _)(l _)(m _)(n _)(o _)(p _)(q _)(r _)
			(s _)(t _)(u _)(v _)(w _)(x _)(y _)(z _))))
	(brute-force  alpha-arr (sort-the-list (doc-to-list document)))
	(write alpha-arr))

(Gen-Decoder-A  *test-doc*)

   
   
		#|((listp (car p))
			(cons (doc-to-list (car p))
				  (doc-to-list (cdr p))))
		(t (cons (append (car p) lst) (doc-to-list((rest p))))))))|#
			

		
