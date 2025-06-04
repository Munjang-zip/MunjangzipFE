

###  auth
- TokenManager : sharedPreference 통해서 토큰 저장/로드
- TokenRefreshHelper.kt : refreshToken으로 accessToken 재발급 처리 부분
- TokenAuthenticator :Retrofit + OkHttp 구조에서 인증실패하면 refresh token 이용해서 access token 갱신하고 재요청시도부분
- StartScreen : 로그인 시작 화면
- GoogleLoginHelper : 구글 로그인 처리 및 서버 Post
- KakaoLoginHelper : 아직 미완성

### data
- Pet.kt : 펫 데이터 
