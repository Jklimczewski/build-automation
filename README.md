# CS studies course "Build automation in IT projects"
## 📖 The project aimed to create a simple CRUD REST API, that should be tested with unit and integration tests. Later, the entire applcation was dockerized and deployed with kubernetes. At the very end I went for CI/CD. Using Jenkins the app is tested and built, an image is pushed to docker hub.

### 📌 Zad 1. Aplikacja

- [x] Przygotuj projekt: Rest Api + Baza Danych
- [x] Napisz unit testy i testy integracyjne do swojej aplikacji
- [x] Wizualizacja api - np. swagger ui, open api
- [x] Przygotuj docker-compose w którym postawić środowisko "lokalne"
- [x] Przygotuj Dockerfile do budowania obrazu aplikacji

### 📌 Zad 2. CI/CD

- [x] Stwórz pipeline multibranch na Jenkins
- [x] Skonfiguruj projekt do triggerowania pipelinów na jenkinskie
- [x] Przygotuj webhooki
- [x] Stwórz pipeline który zbuduje, przetestuje, zbuduje obraz dockerowy
- [x] Dodaj stage do pipeline który dla brancha master wypchnie obraz do docker hub

### 📌 Zad 3. Kubernetes

- [x] Konfiguracja postgres + volumen danych
- [x] Przygotowanie config mapy z parametrami do połączenia aplikacji z bazą danych
- [x] Uruchomienie deploymentu
