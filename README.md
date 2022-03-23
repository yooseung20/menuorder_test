# menu_service

## Flowchart
<img width="80%" src="https://user-images.githubusercontent.com/93904773/159284693-e796ff97-f670-4d8d-a4d4-f2987094e727.JPG"/>

## discription
1. [GET] 기본화면("/") 
   -  Menu Table에 있는 메뉴 목록 출력, button 활성화(로그인 , 장바구니, 주문내역, 계산서)
   -  메뉴이름 선택 ->  "/cart"로 이동

2. [GET] 로그인/회원가입 화면 ("/login")
   - 휴대폰 번호입력, button(로그인) -> UserDto 생성 -> UserRepository 통해 User Table에 접근하여 등록된 사용자인지 확인
      - 2-1. 등록된 사용자 : User -> CustomUserDetails로 return -> security
      - 2-2. 미등록된 사용자 : User table에 사용자 등록(save) ->  CustomUserDetails로 return -> security

3. [POST] jwt token 발급 ("authentication"): "/login"에서 만들어진 UserDto로 token 발행
   - TokenDto 생성
   - Jwtfilter를 통해 발행된 토큰이 유효한지 확인
   - 발행된 jwt를 Security Context에 저장


4. [GET] 기본화면("/")으로 redirect
   - JWT token을 활용하여 로그인 사용자가 있는지 확인
   - 로그인한 사용자로 확인될 경우 -> Menu Table에 있는 메뉴 목록 출력, button 활성화 (로그아웃 , 장바구니, 주문내역, 계산서) 


5. [GET] 장바구니("/cart")
  
  
  
6. [GET] 주문내역("/order")


7. [GET] 계산서("/recipt")



