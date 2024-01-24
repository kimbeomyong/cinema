# 프로젝트 명: Mire Cinema
![movie](https://github.com/wltn3223/cinema/assets/63043594/c47d1d41-c996-42bf-8b7c-09103b8846a6)

# 프로젝트 소개: 회원 가입 후, 영화 예매, 상품 주문을 가능하게 하는 사이트 입니다.

# 개발목적: 

#### 1. 실제 영화사이트가 어떻게 작동하는지 알기 위해, 예매 주문 등 기술 구현을 위함(rest Api로 구현 페이지와 서버를 분리해 따로 만들었습니다.)

# 팀원구성: 문지수, 김범용, 이정욱, 김병준

# 사용기술: SpringBoot & mybatis, Jwt Token, Jquery, SpringSecuriy,html,css,javaScript,BootStrap

# 데이터베이스: 
![물리적모델링](https://github.com/wltn3223/cinema/assets/63043594/cde9d7cf-b011-4cef-9be6-3469fa6bd1fc)

# 요구사항

### 사용자  
#### 로그인, 회원가입 , 회원 crud, 영화 리뷰 crud, 문의 crud, 검색 및 페이징,
#### 카카오 api를 연동한 상품구매, 영화 좌석 예매, 예매내역, 구매내역 조회, 취소  

### 관리자
#### 영화관 조회 수정, 상영관 crud, 영화 스케줄 crud, 공지사항 crud, 문의 답변 crud 검색 및 페이징


# 내가 맡은 파트

#### 회원가입(중복확인, 정규식, spring 유효성 검증), 로그인(스프링 시큐리티와 Jwt Token을 이용한 회원 검증), 회원 crud, 영화 crud
#### 상품 구매, 영화 예매, 구매 및 예매 취소 조회 기능 -> 카카오 api를 통한 결제연동, 취소도 구현
#### 페이징 클래스, 검색도 페이징 처리 
#### Controller Advice를 통한 예외 처리
#### 내가 맡은 부분의 모든 페이지 구현, 모든 통신은 ajax를 통한 비동기화
#### DB 설계 
#### Git Hub 관리 fork를 통해 관리함

# 이 프로젝트에서 중요하게 생각한 부분

#### Jwt Token을 사용해 스프링 시큐리티 필터를 대신함 access Token을 통한 회원검증 token은 로그인 시 쿠키로 발급, 로그아웃 시 쿠키도 삭제함
#### 영화 예매에서 예매 후에 컨트롤러에서 한 번 더 정보 검증을 함 카카오에 전송한 정보가 실제 결제 금액, 주문 ID등이 일치하지 않을 시 주문이 완료되도 바로 취소를 서버에서 실행(상품 주문도 동일)
#### 영화 예매 시 남은 좌석이 줄어들고, 취소 시 좌석이 늘어나야함 
#### 페이징, 검색, 예외처리 등 모두가 사용하는 것들은  클래스, 메서드, SQL을 최대한 재활용 할 수 있게 구현
#### REST api이기 때문에 최대한 서버와 프론트를 분리하고자 함



