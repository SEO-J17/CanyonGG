# 📱CanyonGG

<img src=https://user-images.githubusercontent.com/59912150/231259073-07daa970-eacb-48b8-917c-6a58b689ff17.png width="120" height="120">

+ Riot Games에서 개발한 "리그 오브 레전드"라는 게임의 유저들을 검색해서 게임 전적,매치 정보, 티어 정보등와 같은 게임 정보를 사용자에게 제공하는 프로젝트 입니다.

# 🎤 Introduce

+ 리그 오브 레전드 게임을 즐기는 유저들의 닉네임을 안다면 검색하여 해당 유저의 게임 전적과 같이 플레이한 팀원들의 정보, KDA, 랭크등 다양한 정보를 알아 볼 수 있습니다
+ 유저 정보 등록하기 기능이 있어서 정보를 한 번 등록하면 검색하지 않아도 앱을 실행하자마자 바로 정보를 볼 수 있습니다.
+ 즐겨찾기 기능을 통해 다른 유저를 즐겨찾기에 등록하여 검색을 하지 않아고 한 번의 클릭으로 유저 정보를 알 수 있습니다.
+ 게임 전적을 받아올 때 페이징을 사용했지만 라이엇에서 API 콜 제한을 두었기 때문에 게임 전적 20개만 받아오도록 설정했습니다.

# 🛠 Tech & Library

+ **Min SDK lvevel 26**
+ Kotlin 1.7.0
+ JetPack
    + LifeCycle
    + LiveData
    + flow
    + ViewModel
    + Room
    + DataBinding
    + ViewBinding
    + Navigation
    + DataStore
    + WorkManager
    + Paging3
+ Hilt
+ Glide
+ Retrofit
+ OkHttp
+ Moshi
+ Coroutine
+ SafeArgs
+ Firebase Auth
+ Firebase Remote Config
+ ViewPager2

# ✨Features

> **화면 구성**
<table>
<tr>
<td>홈 화면</td>
<td>검색 화면</td>
<td>설정 화면</td>
</tr>
<tr>
<td>홈 사진</td>
<td>검색 사진</td>
<td>설정 사진</td>
</tr>
</table>

> **로그인, 회원가입 기능**
<table>
<tr>
<td>로그인</td>
<td>회원가입</td>
</tr>
<tr>
<td>홈 사진</td>
<td>검색 사진</td>
</tr>
</table>

> **검색 기능**
<table>
<tr>
<td>유저 검색</td>
<td>검색 결과</td>
</tr>
<tr>
<td>유저 검색 사진</td>
<td>검색 결과 사진</td>
</tr>
</table>

> **즐겨찾기 기능**
<table>
<tr>
<td>즐겨찾기</td>
</tr>
<tr>
<td>즐겨 찾기 결과 사진</td>
</tr>
</table>

> **정보 등록 기능**
<table>
<tr>
<td>정보</td>
</tr>
<tr>
<td>정보 등록 결과 사진</td>
</tr>
</table>

> **전적 상세 화면**
<table>
<tr>
<td>유저 검색</td>
<td>검색 결과</td>
</tr>
<tr>
<td>유저 검색 사진</td>
<td>검색 결과 사진</td>
</tr>
</table>

> **테마 설정**
<table>
<tr>
<td>테마 설정 기능</td>
</tr>
<tr>
<td>테마 설정 기능 사진</td>
</tr>
</table>

# 🎨Structure

+ MVVM 디자인 패턴 + Google Recomanded Architecture를 이용해 앱을 구성했습니다.

> **MVVM**

  <img src=https://user-images.githubusercontent.com/59912150/229727957-6ba9efc6-032c-433e-b4df-e1c9dbcc7595.png>

> **Google Recomanded Architecture**

  <img src=https://user-images.githubusercontent.com/59912150/231258327-b9545768-9f07-4777-ae88-29f9192108e6.png>

+ [google에 권장 가이드](https://developer.android.com/jetpack/guide?hl=ko)에 따라 Presenter -> Doamin ->
  Data 모듈 순으로 아키텍처를 구성했습니다. 각 화살표는 의존성을 나타냅니다.
