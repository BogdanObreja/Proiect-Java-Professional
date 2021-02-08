# Proiect-Java-Professional

Aplicație de gestionare a unei săli de fitness

Se va crea o aplicație ce are ca scop gestiunea oamenilor ce se antrenează într-o sală de fitness.
Există două tipuri de persoane ce pot intra în sală: antrenori și abonați.  Pentru orice persoană se reține adresa de email(trebuie să fie unică), numele, o listă cu toate dățile când a intrat în sală, și un id de tip int, unic. Pentru un antrenor se mai salvează numărul de cursanți(abonați) pe care îi are, iar pentru un abonat se reține progresul făcut, sub forma unei valori din intervalul [0,10]. 
Din cauza restrițiilor cauzate de pandemia COVID-19, în sală pot fi prezenți, la orice moment de timp, maximum 10 oameni. Aplicația poate crea o singură sală de fitness și va exista un newsletter. Toate persoanele care vor da subscribe, vor fi notificate atunci când se adaugă o știre. 
Citirea se va face dintr-un fișier numit input.txt, iar toate mesajele vor fi scrise în fișierul out.txt .
Pe lângă funcționalitate, se vor puncta următoarele aspecte:
se va respecta principiul încapsulării claselor
codul va fi scris respectând regulile de clean code
Folosirea a cel puțin un design pattern

Instrucțiuni de adăugare și care necesită autentificare

SIGNUP_ABONAT email name progress password confirmation_password
dacă există deja abonatul se scrie “abonat deja existent!”
dacă nu există, atunci se adaugă în colecția folosită pentru stocare și se scrie în fișierul de out mesajul: “Abonatul <name> a fost adaugat!”
Se fac urmatoarele verificări:
Password să fie identică cu confirmation_password, altfel se scrie “Parole diferite, nu se poate face adaugarea!”
Parola să aibă minum 8 caractere, altfel se afișează “Parolă prea scurtă!”
Adresa de email să fie una validă, adică să aibă structura ”string@string.string” , altfel se scrie “Adresă de email invalidă”. 
Adresa de email este unică. Nu pot fi doi abonati cu aceeași adresă. Dacă există deja, nu se poate adăuga, afișându-se “Adresa de email este deja utilizata!”
SIGNUP_ANTRENOR email name max_abonati password confirmation_password
Se fac aceleași verificări ca pentru abonați
LOGIN_ABONAT email password
Dacă abonatul nu există se va scrie ”Abonatul nu exista!”
Dacă abonatul există, dar parola este greșită, se afișează ”Parola incorecta!”
Dacă credențialele sunt corecte, abonatul va fi abonatul curent, folosit pentru urmatoarele operații
Dacă există deja un abonat conectat, se afișează ”Alt abonat este deja conectat!”
LOGIN_ANTRENOR email password
Similar, va exista un antrenor current. Se fac aceleași verificări.
LOGOUT_ABONAT email
Dacă abonatul nu era cel logat, se afișează ” abonatul nu era conectat!”
Dacă abonatul era cel conectat, este deconectat (NU mai este abonatul curent și se poate loga alt abonat)
LOGOUT_ANTRENOR email
La fel ca mai sus
INCREMENT_PROGRES value
Dacă abonatul nu este logat, se va afișa  ”Nu există nici un abonat logat!”
Dacă există un abonat curent, atunci se va incrementa progresul cu valoarea data ca parametru. Dacă suma depașește 10, nu se va face incrementarea, ci se va scrie: “Nu se poate face incrementare. Progresul total ar fi ” + suma 
DECREMENT_PROGRES value
Dacă abonatul nu este logat, se va afișa  ”Nu există nici un abonat logat!”
Dacă există un abonat curent, atunci se va decrementa progresul cu valoarea dată ca parametru. Dacă totalul este sub 0, nu se va face operația, ci se va scrie: “Nu se poate face decrementarea . Progresul total ar fi ” + valoare_totala 
ADAUGA_ANTRENOR email
Dacă abonatul nu este logat, se va afișa  ”Nu există nici un abonat logat!”
Abonatul curent va avea ca antrenor pe cel care are adresa de email cea dată ca parametru.
Dacă acel antrenor nu există, se va scrie “Nu exista antrenorul cu emailul <email>”.
Dacă exista antrenorul, dar nu mai are locuri libere se va afișa ”Antrenorul nu mai are locuri libere!”
Dacă se trece de aceste verificări, acel antrenor va fi noul antrenor al abonatului curent. Se va actualiza și lista de abonați a antrenorului.
VIZUALIZARE_ABONATII_MEI
Dacă antrenorul nu este logat, se va afișa  ”Nu există nici un antrenor logat!”
Dacă există, se vor afișa toți abonații acestuia
SUBSCRIBE_ABONAT
Dacă abonatul nu este logat, se va afișa  ”Nu există nici un abonat logat!”
Altfel, acesta se abonează la newsletterul salii (daca nu este deja) și se scrie ”Abonatul cu adresa de email  <email> a fost abonat la newsletter”.
SUBSCRIBE_ANTRENOR
Dacă antrenorul nu este logat, se va afișa  ”Nu există nici un antrenor logat!”
Altfel, acesta se abonează la newsletterul salii (daca nu este deja) și se scrie ”Antrenorul cu adresa de email  <email> a fost abonat la newsletter”.
