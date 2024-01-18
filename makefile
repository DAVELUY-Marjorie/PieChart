# COMMANDES #
JAVAC =javac

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/SAENassim_Marjorie_Lucile
BUILD = build/fr/iutfbleau/SAENassim_Marjorie_Lucile
PATH = fr/iutfbleau/SAENassim_Marjorie_Lucile
DOC = doc/fr/iutfbleau/SAENassim_Marjorie_Lucile

# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -sourcepath src ${SRC}/*.java -encoding UTF-8 -implicit:none
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHOIX NOMS
JAR_NOM = SAE31_2023Nassim_Marjorie_Lucile.jar

# BUTS FACTICES #
.PHONY: run clean doc all

# BUT PAR DEFAUT #
run: Programme1.jar Programme2.jar
	${EXEC_JAR} Programme1.jar
	${EXEC_JAR} Programme2.jar


# AUTRE BUTS
doc:
	-mkdir doc
	javadoc -d doc ${SRC}/*.java 
	javadoc -d doc ${SRC}/*.java 
	javadoc -d doc ${SRC}/*.java

clean:
	rm -rf ${BUILD}/*.class
	rm -rf ./maria
	rm -f Programme1.jar
	rm -f Programme2.jar

# REGLES DE DEPENDANCE #

# PROGRAMME 1 #

## Modele ##

${BUILD}/Arbre.class: ${SRC}/Arbre.java 
	${BUILD}/VueArbre.class \
	${BUILD}/Branche.class \
	${BUILD}/ArbreListener.class \
	${BUILD}/Menu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Arbre.java

${BUILD}/Branche.class: ${SRC}/Branche.java 
	${BUILD}/Menu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Branche.java

#${BUILD}/Modele/Menu.class: ${SRC}/Modele/Menu.java \
	${BUILD}/Modele/Protocole.class \
	${BUILD}/Modele/Modele.class
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Modele/Menu.java#

#${BUILD}/Modele/Modele.class: ${SRC}/Modele/Modele.java 
	#${BUILD}/Modele/Menu.class 
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Modele/Modele.java#

#${BUILD}/Modele/Protocole.class: ${SRC}/Modele/Protocole.java
	#${BUILD}/Modele/Menu.class
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Modele/Protocole.java#

## Vue ## 

#${BUILD}/Vue/VueChoix.class: ${SRC}/Vue/VueChoix.java 
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Vue/VueChoix.java#

#${BUILD}/Vue/VueDonnees.class: ${SRC}/Vue/VueDonnees.java 
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Vue/VueDonnees.java#

#${BUILD}/Vue/VueErreur.class: ${SRC}/Vue/VueErreur.java 
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Vue/VueErreur.java#

${BUILD}/VueArbre.class: ${SRC}/VueArbre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VueArbre.java

## Contrôleur ##

${BUILD}/ArbreListener.class: ${SRC}/ArbreListener.java \
	${BUILD}/Arbre.class \
	${BUILD}/VueArbre.class \
	${BUILD}/Branche.class \
	${BUILD}/Modele.class \
	${BUILD}/TestLauncher.class \
	${BUILD}/VueErreur.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/ArbreListener.java

#${BUILD}/Controleur/BoutonListener.class: ${SRC}/Controleur/BoutonListener.java \
	${BUILD}/Modele/Menu.class \
	${BUILD}/Modele/Modele.class \
	${BUILD}/Controleur/TestLauncher.class \
	${BUILD}/Vue/VueErreur.class 
	#${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BoutonListener.java#

${BUILD}/TestLauncher.class: ${SRC}/TestLauncher.java \
	${BUILD}/Menu.class \
	${BUILD}/VueChoix.class \
	${BUILD}/VueArbre.class \
	${BUILD}/Arbre.class \
	${BUILD}/Modele.class \
	${BUILD}/VueErreur.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/TestLauncher.java

## PROGRAMME 2 ##

## Modele ##

${BUILD}/Menu.class: ${SRC}/Menu.java \
	${BUILD}/Modele.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Menu.java

${BUILD}/Modele.class: ${SRC}/Modele.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Modele.java

${BUILD}/Protocole.class: ${SRC}/Protocole.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Protocole.java

## Vue ##
${BUILD}/Camembert.class: ${SRC}/Camembert.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Camembert.java

${BUILD}/Carre.class: ${SRC}/Carre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Carre.java

${BUILD}/Fleche.class: ${SRC}/Fleche.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Fleche.java

${BUILD}/Legende.class: ${SRC}/Legende.java \
	${BUILD}/Carre.class \
	${BUILD}/Menu.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Legende.java

${BUILD}/Vue/VueChoix.class: ${SRC}/VueChoix.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VueChoix.java

${BUILD}/VueDonnees.class: ${SRC}/VueDonnees.java \
	${BUILD}/Camembert.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VueDonnees.java

${BUILD}/VueErreur.class: ${SRC}/VueErreur.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VueErreur.java

## Controleur ##

${BUILD}/BoutonListener.class: ${SRC}/BoutonListener.java \
	${BUILD}/Menu.class \
	${BUILD}/Modele.class \
	${BUILD}/TestLauncher.class \
	${BUILD}/VueErreur.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BoutonListener.java

${BUILD}/DataViewLauncher.class: ${SRC}/DataViewLauncher.java 
	${BUILD}/VueChoix.class \
	${BUILD}/Menu.class \
	${BUILD}/Modele.class \
	${BUILD}/VueDonnees.class \
	${BUILD}/VueErreur.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/DataViewLauncher.java

${BUILD}/FlecheListener.class: ${SRC}/FlecheListener.java 
	${BUILD}/VueDonnees.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/FlecheListener.java


## MAINS ##
${BUILD}/MainFirst.class: ${SRC}/MainFirst.java \
	${BUILD}/TestLauncher.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MainFirst.java

${BUILD}/MainSecond.class: ${SRC}/MainSecond.java \
	${BUILD}/DataViewLauncher.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MainSecond.java

### Créer les JAR exécutables ###
Programme1.jar: \
		${BUILD}/MainFirst.class \
		${BUILD}/Arbre.class \
		${BUILD}/Branche.class \
		${BUILD}/Menu.class \
		${BUILD}/Modele.class \
		${BUILD}/Protocole.class \
		${BUILD}/VueChoix.class \
		${BUILD}/VueErreur.class \
		${BUILD}/VueArbre.class \
		${BUILD}/ArbreListener.class \
		${BUILD}/BoutonListener.class \
		${BUILD}/TestLauncher.class 
	# Extraire les classes du pilote JDBC MariaDB
	mkdir -p maria
	unzip -q .:/export/documents/maria/mariadb-client.jar -d maria

	# Créer le JAR avec les classes et le pilote JDBC MariaDB
	${JAR} cvfe ${JAR_NOM} fr.iutfbleau.SAENassim_Marjorie_Lucile Programme1.jar -C build ${PATH} -C maria .


Programme2.jar: \
		${BUILD}/MainSecond.class \
		${BUILD}/Menu.class \
		${BUILD}/Modele.class \
		${BUILD}/Protocole.class \
		${BUILD}/Camembert.class \
		${BUILD}/Carre.class \
		${BUILD}/Fleche.class \
		${BUILD}/Legende.class \
		${BUILD}/VueChoix.class \
		${BUILD}/VueDonnees.class \
		${BUILD}/VueErreur.class \
		${BUILD}/DataViewLauncher.class \
		${BUILD}/FlecheListener.class
	
	# Créer le JAR avec les classes et le pilote JDBC MariaDB
	${JAR} cvfe fr.iutfbleau.SAENassim_Marjorie_Lucile Programme2.jar -C build fr ${PATH} -C res/tmp .

