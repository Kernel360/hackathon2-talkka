# 탈까?

## 프로젝트 소개

"빨간 버스... 이 시간에는 탈 수 있을까?"

경기도민은 늘 버스를 기다리며 하루를 허비합니다...

이 프로젝트는 과거의 버스 좌석 기록을 사용자에게 제공하여 보다 더 나은 출퇴근을 도와주는 서비스를 제공합니다.

### 해커톤 내에 개발한 FE
- 미완, 해당 정류장에 도착하는 버스의 좌석수를 간단하게 보여주는 모습
  
|검색|정류장 선택|시간 선택|결과|
|----|-----|----|-|
|![image](https://github.com/user-attachments/assets/3f8bd6e2-5964-47f2-8316-9bc72724ae13)|![image](https://github.com/user-attachments/assets/2524933d-7da0-4330-84f1-51b127236fa7)|![image](https://github.com/user-attachments/assets/8bf8f76e-864b-44db-acc6-cc99f18f8936)|![image](https://github.com/user-attachments/assets/f53876b0-1a58-4138-8517-a6ae848a6807)|


### Grafana 를 통한 view
- 해커톤 이후 간단하게 view 를 보여주기 위해서 grafana 를 이용함.
![image](https://github.com/user-attachments/assets/ca2de74c-9117-4af8-a51f-7e54f7e9858d)


### 개발자
- 박성준 [@JuneParkCode](https://github.com/JuneParkCode) - 팀장
- 김민규 [@kimminkyew](https://github.com/kimminkyeu) - 3~5 일차
- 이선우 [@I-migi](https://github.com/I-migi) - 3~5 일차
- 이강민 [@km2535](https://github.com/km2535) - 1~3 일차
- 송해덕 [@ss0ngcode](https://github.com/ss0ngcode) - 1~3 일차
- 임건우 [@limbaba1120](https://github.com/limbaba1120) 1~3 일차


### 발표 자료
[BE-hackathon-team-1.pdf](https://github.com/user-attachments/files/16377073/BE-hackathon-team-1.pdf)

### 프로젝트 목표

- 해커톤 목표
    - [x] 버스 노선 / 정류장 / 시간대에 따른 좌석 수 5분간 평균 기록 제공
    - [x] 간편한 UI 제공
- 추가 목표
    - [ ] 버스 노선 / 정류장 / 시간에 따른 혼잡도 예측
    - [ ] 다양한 대중교통에 대한 혼잡도 정보 제공

### 프로젝트 기술 스택

- Backend : `Spring Boot 3.3.2` (`Java 17`)
- Frontend : `React.js` (`TypeScript`, `Shadcn ui`)  | `Grafana` ( 해커톤 이후 )
- DB : `MySQL 8.0`
- Infra: `Docker`, `Docker Compose`, `photogrammer.me home-server`

## 문제 해결

### API Key 고갈

- 서비스 특성상 OpenAPI 에 대해서 주기적으로 데이터 수집이 필요합니다.
- 그러나 개발 계정의 경우 일일 최대 1,000 개의 request 를 보낼 수 있습니다.
- 최소한의 api call 으로 제한해야하며, 최대한 많은 request 를 가져와야합니다.

#### 해결방안

- 3분당 1회 요청으로 변경
  - 버스의 특성상 정류장간 이동은 3-15 분, 따라서 최소한으로 Request 를 수행하기 위해 3분 interval 설정.
- 팀원 모두 API Key 를 발급 받고 Rolling 할 수 있도록 변경
  - 일일 1,000 건 -> 일일 1,000 * N 건
- 버스 운행이 종료될 경우 Request를 하지 않도록 변경
  - 새벽 시간대에 불필요한 Request `(해당 버스의 운행 종료 시간 / 3분) * 추적 중인 버스 개수 `감소
- 추적 중인 버스 정류장에 대해서는 데이터 수집 요청시 모든 정류장을 불러오도록 변경, 이후에는 DB 에서 데이터 획득
  - `경유 정류장 정보` API 에 대한 Request 를 줄일 수 있었음.


### 공공데이터 OpenAPI 요청 서비스 코드 중복

- 공공 데이터 API 에 대한 요청 서비스 코드의 경우 다음과 같은 코드로 이루어짐
  - 서비스 키 획득
  - URI 설정
  - Query Parameter 설정
  - Request / Response 처리

- 또한, API 의 변동 가능성 / 서비스 키 변동 가능성 / Request / Respone 에 따라 다른 Object 변환 등이 고려됨.
- 따라서 중복되는 코드를 줄이고 추상화와 외부에서 값을 최대한 주입받아 사용할 수 있도록 변경의 필요가 있었음.

#### 해결방안

- API Request 를 하는 Service code 추상화
  - 기존 : 각 서비스별 하드코딩
    - 유연성이 부족하며, 코드를 읽는데 어려움이 있었음.
    - 코드 작성에 시간 소요.
  - 변경 : Key, URL 을 주입받고, Abstract class 를 상속하여 사용할 수 있도록 변경
    - [AbstractOpenApiService](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/service/AbstractOpenApiService.java)
    - [구현 - BusLocationApiService](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/service/BusLocationApiService.java)
 
- API Key 주입 방식 변경
  - 기존 : 하드 코딩 (서비스 키가 코드 내에 있었으며, repository 에 노출되는 위험 존재)
  - 변경 : Property 로 부터 값을 주입받도록 변경 / Rolling key를 지원하는 interface 를 implement 하도록 변경
    - [ApiServiceKeyPropertyInterface](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/configuration/ApiServiceKeyPropertyInterface.java)
    - [구현 - PublicApiServiceKeyProperty](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/configuration/PublicApiServiceKeyProperty.java)
 
- API 요청 경로 Property 화
  - 기존 : 하드코딩
  - 변경 : application.yaml + property 를 통해서 해당 값을 주입받고, interface 로 `AbstractOpenApiService` 에서 사용할 수 있도록 변경
    - [ApiPropertyInterface](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/configuration/ApiPropertyInterface.java)
    - [BusRouteLocationApiProperty](https://github.com/Kernel360/hackathon2-talkka/blob/ce13609bd5ebd8e168c0a27a9cd76b5b363bdaa8/bus-seat/src/main/java/org/kernel360/busseat/openapi/configuration/BusRouteLocationApiProperty.java)
   
- 효과
  - ApiService 작성 시간 감소
  - 빌드 이후에도 Key, URI 변경 가능 / Repository 에 Key가 더 이상 노출되지 않음.

### Timezone 문제

- Server - DB 의 TZ 불일치로 인해 잘못된 시간값이 주입.

#### 해결 방안

- 완벽하게 해결된 상태 아님.
- 현재는 Server / DB 의 TZ 를 `Asia/Seoul` 으로 지정하여 해결함.
  - Front 에 넘어갈 때는 가공되어 보여지기 때문에 문제가 발생하지 않음.
- 그러나, Grafana 연동 중 DB에서 가져온 데이터를 `UTC` 로 해석하여 TZ 를 `Asia/Seoul` 으로 설정하면 재해석되어 보이는 문제를 발견함.
  - 데이터 저장시 Zone 을 명시할 필요.

## 프로젝트 개발 가이드

### 팀 개발 환경 가이드

- JDK : Corretto 17
- IntelliJ 설정
    - Code Formatter 설정
        - ./.idea 에 위치한 `naver-intellij-formatter.xml` 파일을 이용하여 설정
        - Settings -> CodeStyle -> Java -> Import Scheme
    - CheckStyle 설정
        - Plugin CheckStyle 설치
        - ./.idea 에 위치한 `naver-checkstyle-rules.xml` 파일을 이용하여 설정
        - suppression file 의 경우 `./.idea/naver-checkstyle-suppresssions.xml` 지정
    - EnvFile 설정
        - `.env.sample` 토대로 `.env` 파일 생성
        - Plugin 설치 (EnvFile)
        - Run -> Edit Configurations -> EnvFile 활성화 / `.env` 파일 지정

### 프로젝트 실행

- 주의: JPA DDL 활성화하지 않음. 반드시 아래 DB 설정을 수행할 것.

```shell
$ cd ./bus-seat
$ ./gradlew clean build --exclude-task test
$ cd ..
$ docker compose up -d
```

### DB 설정

- 기본 DB 설정 script
    - 실제 정보 (7800 번의 24/07/24 14:30 ~ 23:59) 의 데이터를 바탕으로 구성된 데이터를 로드합니다.

```shell
$ docker exec -it bus-mysql sh
# docker container 안의 작업입니다.
$ mysql -u root -p
# 비밀번호 입력
$ source /var/lib/mysql-files/init_db.sql
# schema 초기화 확인 
$ show tabels;
```

### API Key Rolling

- `application.yaml` 과 `.env` 의 설정으로 추가 키를 설정할 수 있습니다.


#### Example
**`application.yaml`**

- openapi 부분에서 keys 에 추가합니다.

```yaml
openapi:
  public:
    route-location:
      host: apis.data.go.kr
      path: /6410000/buslocationservice/getBusLocationList
    route-info-item:
      host: apis.data.go.kr
      path: /6410000/busrouteservice/getBusRouteInfoItem
    bus-route-station:
      host: apis.data.go.kr
      path: /6410000/busrouteservice/getBusRouteStationList
    bus-route-list:
      host: apis.data.go.kr
      path: /6410000/busrouteservice/getBusRouteList

    service-key:
      keys:
        - ${SERVICE_KEY_1} # Key 하나는 있어야함
        - ${SERVICE_KEY_2} # Key 추가...
        - ${SERVICE_KEY_3}
        - ${SERVICE_KEY_4} 
```

**`.env`**

- 위에서 지정한 env 값을 추가합니다.

```env
MYSQL_USERNAME=root
MYSQL_PASSWORD=1234
MYSQL_DATABASE=BUS_DB
MYSQL_URL=jdbc:mysql://localhost:3306/BUS_DB?createDatabaseIfNotExist=true
SERVICE_KEY_1=encodedkey
SERVICE_KEY_2=encodedkey2   <<< 이렇게 Key 를 추가합니다.
SERVICE_KEY_3=encodedkey3
SERVICE_KEY_4=encodedkey4
```
