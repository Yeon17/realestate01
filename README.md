# realestate01

### 공인중개사 사무소 홈페이지 제작

#### 구현한 게시판 및 페이지 : 
로그인 페이지, 회원가입 페이지, 매수/매도하기, 아파트 시설 정보, 부동산 매물 게시판, 공지사항 게시판, 질의응답 게시판, 개인정보 수정 및 탈퇴 페이지 등
<br>
<br>
#### 사용자
<br>
1. 홈페이지를 통해 공인중개사 사무소에 매물의 매수, 매도 의뢰를 넣을 수 있고 관리자가 등록한 매물정보를 아파트 별로 조회할 수 있다. <br>
2. 다른사람의 질의응답과 관리자가 작성한 공지사항을 조회할 수 있다.<br>
3. 회원가입, 로그인을 하여 권한을 부여받을 수 있다. 일반 회원가입을 하여 로그인을 할 수 있고, 소셜 로그인을 할 수도 있다.<br>
4. 원하는 매물을 찜할 수 있고 자신이 찜한 매물 목록을 조회하거나 찜목록에서 삭제할 수 있다. <br>
5. 질문 게시판에 질문글을 올려 관리자의 답변을 받을 수 있다. 자신이 올린 글은 수정하거나 삭제 할 수 있다.<br>
6. 비밀번호를 잊어버린 경우 가입한 이메일로 임시 비밀번호를 발급받을 수 있다.<br>
7. 개인정보를 수정하거나 계정을 탈퇴할 수 있다.<br>
<br>

#### 관리자
<br>
1. 사용자가 의뢰한 매물의 매수/매도 정보를 조회할 수 있다. <br>
2. 매물 게시판에 매물정보를 등록할 수 있다.<br>
3. 공지사항 게시판에 글을 올릴 수 있고, 질의응답 게시판의 질문글에 답변을 달 수 있으며 질의응답 게시판에 회원이 올린 글들을 관리자의 권한으로 삭제 할 수 있다.<br>
4. 관리자가 등록한 매물이나 공지사항, 질문에 대한 답변은 글을 작성한 관리자가 수정하거나 삭제 할 수 있다.<br>
5. 회원가입한 사용자들을 조회하고 탈퇴시킬 수 있다.<br>
<br>
<br>

#### 도메인 :<br>
(realestate01\src\main\java\com\realestate01\springboot\domain) <br>
프로젝트의 각 엔티티와 레포지토리<br>

  + property : 부동산 매물 정보<br>
  + request : 사용자가 요청한 매수/매도 정보<br>
  + user : 회원 엔티티<br>
  + cart, cartProduct : 찜한 매물을 담는 cart와 cart에 담긴 매물 cartProduct<br>
  + notice : 관리자가 올릴 수 있는 공지사항<br>
  + question : 회원이 작성할 수 있는 질문글<br>
  + answer : 회원의 question에 대해 관리자가 작성할 수 있는 답변<br>
  
#### 서비스 : <br>
(realestate01\src\main\java\com\realestate01\springboot\service)<br>
각 도메인의 삽입, 수정 삭제 등 레포지토리를 이용하여 각 도메인고 관련된 서비스 수행<br>

  + RequestService <br>
  + PropertyService<br>
  + UserService : UserDetailsService구현.<br>
  + CartService<br>
  + NoticeService <br>
  + QuestionService<br>
  + AnswerService <br>
  + SendMailService : 임시 비밀번호를 생성하여 회원의 비밀번호를 업데이트하고 메일을 보낸다.<br>

#### 컨트롤러 :<br>
(realestate01\src\main\java\com\realestate01\springboot\web)<br>
각 도메인의 삽입, 수정, 삭제 등 관련 서비스를 요청하는 컨트롤러<br>

  + RequestApiController <br>
  + PropertyApiController <br>
  + UserController <br>
  + CartApiController<br>
  + NoticeApiController<br>
  + QuestionApiController<br>
  + AnswerApiController<br>
  + ImageController : <br>
  + FindPwdController : userService에 이메일 및 이름의 일치 여부 비교를 요청하고 mailService에 메일 발송 요청<br>
  + IndexController : 프로젝트 홈페이지의 템플릿과 url 매핑<br>
  
#### dto : <br>
(realestate01\src\main\java\com\realestate01\springboot\web\dto)<br>
도메인 서비스에 관한 요청을 처리하기 위해 데이터를 받거나 응답 데이터를 전달하기 위한 객체<br>

#### CustomPagination : <br>
(realestate01\src\main\java\com\realestate01\springboot\web\dto\CustomPagination.java)<br>
게시판의 페이징 처리를 위한 클래스. <br>
총 페이지 수, 현재 페이지 번호, 페이지에 표시될 첫 페이지와 끝 페이지의 번호, 페이지에 표시될 페이지 번호의 배열, 이전 페이지와 다음 페이지 버튼을 표시할 지에 대한 boolean 객체 등의 멤버 변수를 가진다. IndexController에서 생성되어 페이징을 처리한다.<br>

#### 소셜 로그인 및 보안 관련 설정 : <br>
(realestate01\src\main\java\com\realestate01\springboot\config)<br>
 
  + SecurityConfig.java : 스프링 시큐리티를 이용하여 자원 및 페이지 접근에 대한 권한 설정, oauth2.0와 폼로그인에 대한 설정<br>
  + CustomOAuth2UserService.java : Oauth2.0 로그인 서비스. OAuth2UserService를 구현하여 loadUser 오버라이드<br>
  + OAuthAttributes.java : Oauth로그인 시 user 엔티티에 저장하거나 업데이트 하기 위한 dto<br>
  + SessionUser.java : 로그인 성공 시 세션에 저장할 user 값을 담기 위한 dto<br>
  + LoginUser.java : 로그인 성공 시 메소드 인자로 세션값을 바로 받도록 @LoginUser 어노테이션 생성<br>
  + LoginUserArgumentResolver.java : HandlerMethodArgumentResolver 인터페이스 구현. 파라미터에 @LoginUser 어노테이션이 붙어 있고, 파라미터 클래스 타입이 SessionUser일 경우 "user"의 세션 값을 주입하도록 설정<br>
  + LoginFailHandler.java :폼 로그인 실패 시 에러 메시지 출력하는 핸들러<br>      
<br>
<br>

#### css, js, img 등의 리소스:<br>
(realestate01\src\main\resources\static)<br>
  js : 각 도메인에 대한 삽입, 수정, 삭제 등을 ajax를 통해 요청하는 js파일들.<br>
  
   + index.js : summernote에 대한 설정<br>
   + main.js <br>
   + properties.js<br>
   + request.js<br>
   + cart.js<br>
   + notice.js<br>
   + question.js<br>
   + anwser.js<br>
   + sign-up.js : user 엔티티에 대한 요청을 ajax로 처리<br>
   + find-pwd.js : 임시 비밀번호 메일 발송 요청을 ajax로 처리<br>
  
#### templates : <br>
(realestate01\src\main\resources\templates)<br>
mustache로 구현한 사이트의 페이지들<br>

  + index.mustache : 메인 페이지<br>
  
  + find-way.mustache : 공인중개사 사무소의 위치<br>
  + apart-view.mustache : 아파트 단지 전경<br>
  + area-info.mustache : 아파트 평형 정보<br>
  + in-facility.mustache : 아파트 내부 시설<br>
  + community-facility.mustache : 아파트 커뮤니티 시설<br>
  + surrounding-location.mustache : 아파트 주변 입지<br>
  
  + request.mustache : 관리자가 사용자가 의뢰한 매수/매도 정보를 조회할 수 있음<br>
  + request-buy.mustache : 사용자 매수 의뢰<br>
  + request-sell.mustache : 사용자 매도 의뢰<br>
  
  + property.mustache : 관리자가 입력한 매물 정보의 목록를 조회할 수 있는 페이지<br>
  + property-enroll.mustache : 관리자가 매물 정보를 입력할 수 있음<br>
  + property-detail.mustache : 자세한 매물정보를 조회<br>
  + property-update.mustache : 관리자가 자신이 입력한 매물 정보를 수정하는 페이지<br>
  
  + notice.mustache : 공지사항 목록을 조회하는 페이지<br>
  + notice-enroll.mustache : 관리자가 공지사항을 작성하는 페이지<br>
  + notice-detail.mustache : 공지사항의 상세내용을 조회<br>
  + notice-update.mustache : 관리자가 자신이 작성한 공지사항 수정하는 페이지<br>
 
  + question.mustache : 질문글 목록을 조회하는 페이지<br>
  + question-enroll.mustache : 로그인한 회원이 질문글을 작성하는 페이지<br>
  + question-detail.mustache : 질문글의 상세내용을 조회<br>
  + question-update.mustache : 작성자가 자신이 작성한 질문글을 수정하는 페이지<br>
  
  + login.mustache : 로그인 페이지<br>
  + sign-up.mustache : 회원가입 페이지<br>
  + find-pwd.mustache : 비밀번호를 찾는 페이지. 이메일과 이름을 입력하고 임시 비밀번호 발송 요청<br>
  + account.mustache : 회원이 자신의 계정정보를 확인하고 수정하는 페이지<br>
  + member-management.mustache : 관리자가 회원들을 관리하는 페이지<br>
  + cart.mustache : 회원이 자신이 찜한 매물목록을 조회하는 페이지<br>
  
<br>

#### 앞으로 구현하면 좋은 것들 : 
회원가입 시 휴대폰 번호 입력 추가, 휴대폰 번호로 아이디(이메일) 찾기, 토큰 기반 인증 시스템으로 변경(jwt토큰 사용)

