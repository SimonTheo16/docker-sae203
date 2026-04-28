# docker-sae203Instructions pour lancer l'application

Vérifiez si docker est installé :

	docker --version

Cloner le référentiel :

	git clone git@github.com:SimonTheo16/docker-sae203.git

Aller au référentiel :

	cd TP/s2/s2.03_install_services_reseaux

Construisez l'image décrite dans dockerfile avec docker build :

	docker build -t <choisir-un-nom-pour-l'image> .

Lancer le serveur web :

	docker run -d -p 8888:80 <nom-de-l'image-choisie>

Vérifier que l'application est en cours d'exécution. Pour ce faire, ouvrez un navigateur et tapez http://di-docker:8888

Vérifier que le conteneur associé est actif :

	docker ps

La sortie de docker ps doit être similaire à :

CONTAINER ID   IMAGE               COMMAND                  CREATED          STATUS          PORTS                           NAMES
9f4ae517387b   docker-sae203       "docker-php-entrypoi…"   30 seconds ago   Up 29 seconds   90/tcp, 0.0.0.0:8888->80/tcp    boring_kalam

Finalement, arrêtez le conteneur avec la commande suivante (les dernières chiffres sont le code de hachage affiché par docker ps):

docker stop 9f4ae517387b
