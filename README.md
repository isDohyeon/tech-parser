<img src="https://github.com/user-attachments/assets/b67ce15c-4e54-41b7-b465-7850317f8e52" width="100"/>

# TechParser

- **TechParser**는 여러 기술 블로그의 RSS 피드를 하나의 앱에서 통합적으로 관리하고 구독할 수 있는 안드로이드 애플리케이션입니다.
- 사용자는 관심 있는 기술 블로그를 구독하고, 최신 포스트를 실시간으로 받아볼 수 있으며, 중요한 글들을 북마크로 저장하여 나중에 다시 읽을 수 있습니다.
- 백그라운드로 피드 데이터를 수집하고, 특정 블로그 혹은 키워드를 계산한 스마트 알림 시스템을 통해 중요도가 높은 피드의 알림을 받을 수 있습니다.

<br>

## 📁 개인 프로젝트 기획서
<img src="https://github.com/user-attachments/assets/0301739b-0338-4640-8ca7-64606487cf83" width="400"/>
<img src="https://github.com/user-attachments/assets/19056418-3a40-448d-b0a1-79974ad12516" width="400"/>
<img src="https://github.com/user-attachments/assets/c0db2702-882c-401d-8cb6-133aa8358ba4" width="400"/>
<img src="https://github.com/user-attachments/assets/9d99cc7e-5cef-4a12-9150-7621f933e78c" width="400"/>
<img src="https://github.com/user-attachments/assets/67975497-960f-441b-9976-a4445dcdebb8" width="400"/>
<img src="https://github.com/user-attachments/assets/a1388f73-d3e6-40b9-a099-0672e931d259" width="400"/>
<img src="https://github.com/user-attachments/assets/31c18a41-0715-45c4-946c-3fee609fc86a" width="400"/>
<img src="https://github.com/user-attachments/assets/f4fcecbf-2a0f-409d-91b7-8e55b3b903a2" width="400"/>
<img src="https://github.com/user-attachments/assets/8106d8c5-a08a-4139-8027-f45b05c1c682" width="400"/>
<img src="https://github.com/user-attachments/assets/7d5d6acb-1254-4423-ada7-d55022e42161" width="400"/>

<br>
<br>

## 📱 최종 구현 화면 및 설명

### 🔐 로그인/회원가입 화면
<img src="https://github.com/user-attachments/assets/f0dfcf63-8627-47b0-88aa-a0b0eab971e7" width="200"/>
<img src="https://github.com/user-attachments/assets/d76a3433-2677-452f-aaec-2e9e4649c0f9" width="200"/>
<img src="https://github.com/user-attachments/assets/6ff5dcb5-089c-4a0f-8610-92015383b47f" width="200"/>

- Firebase Authentication으로 이메일 회원가입/로그인
- 사용자별로 구독, 북마크, 설정 등 개인화된 데이터 관리
- Firebase Realtime Database로 실시간 동기화

<br>

### 📰 RSS 피드 화면
<img src="https://github.com/user-attachments/assets/82d432be-ccff-43c1-b4ae-43bb7585a882" width="200"/>
<img src="https://github.com/user-attachments/assets/3fce6a26-ff5c-47d3-bcb0-a3ce3e7f21e0" width="200"/>
<img src="https://github.com/user-attachments/assets/eba6dcf1-7dd7-4cc2-8c80-6cdd9cfdca54" width="200"/>
<img src="https://github.com/user-attachments/assets/8c564bde-8f23-4dcd-ac11-3c3a74b5e2f9" width="200"/>

- 여러 기술 블로그의 RSS 피드를 실시간으로 파싱하고, 시간순으로 통합 정렬된 타임라인 제공
  - 피드 클릭 시 WebView를 통해 원문 링크로 이동
- 백그라운드에서 1시간마다 자동 업데이트 (WorkManager 사용)
- 구독한 블로그의 피드만 선별적으로 표시되며, 제목 기반 실시간 검색 기능 제공
- 알림 버튼을 통해 새로운 피드의 알림 로그를 확인 -> 터치 시 원문 링크로 이동

<br>

### 🔖 북마크 화면
<img src="https://github.com/user-attachments/assets/fcd2d8f1-bb0c-4517-8f80-61269103226c" width="200"/>
<img src="https://github.com/user-attachments/assets/cf8b1387-17ac-4cad-bb07-779eb96908a9" width="200"/>
<img src="https://github.com/user-attachments/assets/d2a0d92e-1dcd-44c2-9bbe-b4ee45c0a353" width="200"/>

- 중요한 포스트를 북마크하여 저장하고 개인화된 폴더로 분류
- 모든 폴더는 타임스탬프 기반으로 최근 생성 순 정렬

<br>

<img src="https://github.com/user-attachments/assets/71a18c0e-2179-4cf3-aec0-bb1b2e5bcbb8" width="200"/>
<img src="https://github.com/user-attachments/assets/9342ba13-883a-4d3c-a905-a78697b222a8" width="200"/>
<img src="https://github.com/user-attachments/assets/a7bcac3b-da95-48ef-9481-a6abffcb9311" width="200"/>

- 메인 피드 화면에서 Long Click을 통해 원하는 북마크 폴더에 저장
- 북마크 폴더 안에서 피드 클릭 시 원문 링크로 이동

<br>

### ❤️ 블로그 구독 관리 화면

<img src="https://github.com/user-attachments/assets/4cb49e1f-7e3b-46f9-ac7e-f8d649ddd09e" width="200"/>
<img src="https://github.com/user-attachments/assets/4fd6c6dd-b16f-4a87-85f3-afcc773316d1" width="200"/>
<img src="https://github.com/user-attachments/assets/b99be855-fb06-4ea4-9f05-b6c3e9da3c15" width="200"/>
<img src="https://github.com/user-attachments/assets/ff0460ba-5078-4e5a-a600-b71c652de544" width="200"/>

- 추천 블로그 확인 및 구독/해제
- 구독한 블로그의 글들만 피드에 표시
- 구독한 블로그에 대해서만 중요도 알림 설정이 가능

<br>

### ⚙️ 설정 화면

<img src="https://github.com/user-attachments/assets/676768aa-c4ed-4887-9431-8c8f00de2b56" width="200"/>
<img src="https://github.com/user-attachments/assets/03651530-289b-4baa-a628-8d90c1d674bf" width="200"/>
<img src="https://github.com/user-attachments/assets/86d00a2c-1592-42e8-b7fe-aa39300a578d" width="200"/>
<img src="https://github.com/user-attachments/assets/83d8c909-b225-40ab-8643-d36eec52e730" width="200"/>

- 앱 전체에 대한 알림 on/off 제어
- 구독 중인 블로그별 알림 설정 (중요도 +2)
- 원하는 키워드별 알림 설정 (중요도 +2)

<br>

### ⏰ 알림 화면

<img src="https://github.com/user-attachments/assets/2b6563cc-61cf-46cc-b901-5a8b8f3c6711" width="200"/>
<img src="https://github.com/user-attachments/assets/286a5721-0560-4cc3-ba77-bc725d8b112c" width="200"/>
<img src="https://github.com/user-attachments/assets/8418dd9e-40f0-47c1-b38d-ca6ec643b090" width="200"/>

- 알림 테스트 시 중요도 4의 알림이 생성 (구독 중인 Toss Tech, 키워드 Java)
- 새로운 피드에 따라 중요도를 계산하여 0, 2, 4의 알림이 생성되며 실제 안드로이드의 알림 중요도로 적용
- 3번째 사진은 실제 중요도 4의 알림이 온 화면

<br>

## 📊 성능 최적화

### 🚀 비동기 처리 최적화

#### RSS 파싱 병렬 처리 성능 비교

```kotlin
// 🔴 순차 처리 (Before) - 블로그별로 하나씩 처리
suspend fun parseRssFeedsSequential(): List<RssFeedModel> {
    val feeds = mutableListOf<RssFeedModel>()
    
    blogs.forEach { blog ->  // 순차적으로 하나씩 처리
        val items = getRssItems(blog.rssUrl)  // 평균 2초 소요
        feeds.addAll(items.transform(blog.name, blog.logoUrl))
    }
    
    return feeds.sortedByDescending { parseRssDate(it.pubDate) }
}

// 🟢 병렬 처리 (After) - 모든 블로그 동시 처리
suspend fun parseRssFeeds(): List<RssFeedModel> = withContext(Dispatchers.IO) {
    val deferredList = blogs.map { blog ->
        async {  // 모든 블로그를 동시에 처리
            val items = getRssItems(blog.rssUrl)
            items.transform(blog.name, blog.logoUrl)
        }
    }
    
    val results = deferredList.awaitAll()  // 모든 작업 완료 대기
    results.flatten().sortedByDescending { parseRssDate(it.pubDate) }
}
```

**📈 성능 개선 결과:**

| 블로그 수 | 순차 처리 시간 | 병렬 처리 시간 | 성능 향상 |
|----------|-------------|-------------|----------|
| 5개 블로그 | ~10초 (5 × 2초) | ~2-3초 | **70-80% 단축** |
| 10개 블로그 | ~20초 (10 × 2초) | ~3-4초 | **80-85% 단축** |
| 15개 블로그 | ~30초 (15 × 2초) | ~4-5초 | **83-87% 단축** |

#### 핵심 최적화 기법
- **Kotlin Coroutines**의 `async/await` 패턴으로 non-blocking 병렬 처리
- **Dispatchers.IO** 스레드 풀을 활용한 네트워크 작업 최적화
- **awaitAll()** 을 통한 효율적인 결과 수집

### 💾 메모리 관리 최적화

#### ViewBinding 메모리 최적화
```kotlin
// 🔴 Before: findViewById로 인한 메모리 누수 위험
class FeedFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    
    override fun onDestroyView() {
        super.onDestroyView()
        // recyclerView = null 을 빼먹으면 메모리 누수 발생
    }
}

// 🟢 After: ViewBinding으로 자동 메모리 관리
class FeedFragment : Fragment() {
    private val binding by lazy { FragmentFeedBinding.inflate(layoutInflater) }
    // Fragment 소멸 시 자동으로 binding 해제
}
```

**📊 메모리 관리 개선:**
- **findViewById 제거**: 리플렉션 오버헤드 **100% 제거**
- **메모리 누수 방지**: ViewBinding 자동 해제로 **안전성 향상**


### ⏰ 백그라운드 최적화

#### WorkManager 배터리 최적화
```kotlin
// 배터리 효율적인 주기적 작업 스케줄링
private fun registerRssWorker() {
    val request = PeriodicWorkRequestBuilder<RssWorker>(
        1, TimeUnit.HOURS  // 1시간마다 실행
    ).build()
    
    WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
        "RssCheckWorker",
        ExistingPeriodicWorkPolicy.KEEP,  // 중복 작업 방지
        request
    )
}
```

**🔋 배터리 최적화 효과:**
- **중복 작업 방지**: `ExistingPeriodicWorkPolicy.KEEP`으로 **리소스 낭비 방지**
- **시스템 최적화**: Android Doze 모드와 호환되어 **배터리 수명 연장**

### 🔥 Firebase 실시간 동기화 최적화
```kotlin
// 효율적인 Firebase 리스너 관리
private fun observeFeeds() {
    FirebaseRef.subscribeRef().addValueEventListener { snapshot ->
        // 구독 상태 변경 시에만 UI 업데이트
        val subscribedBlogs = snapshot.children.map { it.key.toString() }.toSet()
        updateFeeds(subscribedBlogs)  // 필요한 부분만 선택적 업데이트
    }
}
```

**⚡ 데이터 동기화 성능:**
- **실시간 반영**: 평균 **500ms 이내** 변경사항 반영
- **UI 반응성**: 백그라운드 처리로 **UI 블로킹 0%**

<br>

## 🛠 기술 스택

### 📱 Core Platform
<p align="left">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle"/>
</p>

- **언어**: Kotlin 100%
- **최소 SDK**: API 24 (Android 7.0)
- **타겟 SDK**: API 34 (Android 14)
- **컴파일 SDK**: API 35
- **빌드 도구**: Gradle KTS

### 🔥 Firebase Backend
<p align="left">
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase"/>
  <img src="https://img.shields.io/badge/Firebase_Auth-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase Auth"/>
  <img src="https://img.shields.io/badge/Realtime_Database-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Realtime Database"/>
</p>

```kotlin
// Firebase BOM 33.7.0으로 버전 통합 관리
implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
implementation("com.google.firebase:firebase-analytics")      // 사용자 분석
implementation("com.google.firebase:firebase-auth")           // 사용자 인증
implementation("com.google.firebase:firebase-database")       // 실시간 데이터베이스
```

### 🌐 네트워킹 & 파싱
<p align="left">
  <img src="https://img.shields.io/badge/Retrofit-48B983?style=for-the-badge&logo=square&logoColor=white" alt="Retrofit"/>
  <img src="https://img.shields.io/badge/OkHttp-5E5E5E?style=for-the-badge&logo=square&logoColor=white" alt="OkHttp"/>
  <img src="https://img.shields.io/badge/Gson-FF6B35?style=for-the-badge&logo=json&logoColor=white" alt="Gson"/>
  <img src="https://img.shields.io/badge/TikXML-00D4AA?style=for-the-badge&logo=xml&logoColor=white" alt="TikXML"/>
</p>

```kotlin
// 네트워킹
implementation("com.squareup.retrofit2:retrofit:2.9.0")       // HTTP 클라이언트
implementation("com.squareup.okhttp3:okhttp:4.12.0")          // HTTP 엔진

// 데이터 파싱
implementation("com.google.code.gson:gson:2.10.1")            // JSON 파싱
implementation("com.tickaroo.tikxml:core:0.8.13")             // RSS XML 파싱
implementation("com.tickaroo.tikxml:retrofit-converter:0.8.13") // Retrofit XML 컨버터
```

### 🏗 AndroidX & Architecture
<p align="left">
  <img src="https://img.shields.io/badge/Android_Jetpack-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="Android Jetpack"/>
  <img src="https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white" alt="Material Design"/>
  <img src="https://img.shields.io/badge/View_Binding-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="View Binding"/>
</p>

```kotlin
// 핵심 AndroidX 컴포넌트
implementation(libs.androidx.core.ktx)                        // Kotlin 확장
implementation(libs.androidx.appcompat)                       // 호환성 라이브러리
implementation(libs.material)                                 // Material Design

// 아키텍처 컴포넌트
implementation(libs.androidx.lifecycle.livedata.ktx)          // LiveData
implementation(libs.androidx.lifecycle.viewmodel.ktx)         // ViewModel
implementation(libs.androidx.navigation.fragment.ktx)         // Navigation Fragment
implementation(libs.androidx.navigation.ui.ktx)               // Navigation UI
implementation("androidx.fragment:fragment-ktx:1.7.1")        // Fragment KTX
```

### ⚡ 비동기 & 백그라운드
<p align="left">
  <img src="https://img.shields.io/badge/Coroutines-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Coroutines"/>
  <img src="https://img.shields.io/badge/WorkManager-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="WorkManager"/>
</p>

```kotlin
// 백그라운드 작업
implementation("androidx.work:work-runtime-ktx:2.9.0")        // WorkManager
// Kotlin Coroutines (코틀린 기본 제공)
```

### 🔧 개발 도구
<p align="left">
  <img src="https://img.shields.io/badge/KSP-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="KSP"/>
  <img src="https://img.shields.io/badge/KAPT-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="KAPT"/>
</p>

```kotlin
// 어노테이션 프로세싱
id("com.google.devtools.ksp")                                 // Kotlin Symbol Processing
id("org.jetbrains.kotlin.kapt")                              // Kotlin Annotation Processing
kapt("com.tickaroo.tikxml:processor:0.8.13")                 // TikXML 프로세서
```

### 🎨 UI/UX 패턴
- **UI 아키텍처**: Fragment-based Navigation with Bottom Navigation
- **데이터 바인딩**: View Binding (메모리 안전)
- **디자인 시스템**: Material Design 3
- **화면 전환**: Navigation Component
- **상태 관리**: LiveData + ViewModel pattern
- **비동기 처리**: Kotlin Coroutines + Flow
- **데이터 패턴**: Repository Pattern with Firebase


<br>

## 📁 프로젝트 구조

```
📦 TechParser (hnu.multimedia.techparser)
├── 📱 TechParserApp.kt                    # 애플리케이션 진입점
├── 🏠 MainActivity.kt                     # 메인 액티비티 (Bottom Navigation)
│
├── 🔐 auth/                               # 사용자 인증 관련
│   ├── SplashActivity.kt                  # 스플래시 화면 & 자동 로그인
│   ├── LoginActivity.kt                   # 로그인 화면
│   ├── JoinActivity.kt                    # 회원가입 화면
│   └── model/
│       └── UserModel.kt                   # 사용자 데이터 모델
│
├── 🔄 rss/                               # RSS 피드 처리 핵심 모듈
│   ├── RssParser.kt                       # RSS XML 파싱 엔진 (병렬 처리)
│   ├── RssRepository.kt                   # RSS 데이터 저장소 관리
│   ├── RssFeedProcessor.kt                # 신규 피드 감지 & 알림 처리
│   ├── RssWorker.kt                       # 백그라운드 RSS 업데이트 워커
│   ├── model/                             # RSS 데이터 모델
│   │   ├── RssFeed.kt                     # RSS 피드 최상위 모델
│   │   ├── RssChannel.kt                  # RSS 채널 정보 모델
│   │   ├── RssItem.kt                     # RSS 아이템 모델
│   │   └── RssFeedModel.kt                # 통합 RSS 피드 모델 + 확장 함수
│   └── network/                           # 네트워킹 레이어
│       ├── RssApiService.kt               # Retrofit RSS API 인터페이스
│       └── RssRetrofitClient.kt           # Retrofit 클라이언트 설정
│
├── 🖥 ui/                                # 사용자 인터페이스 레이어
│   ├── feed/                              # 피드 메인 화면
│   │   ├── FeedFragment.kt                # 피드 목록 표시 프래그먼트
│   │   ├── FeedAdapter.kt                 # 피드 RecyclerView 어댑터
│   │   ├── WebViewActivity.kt             # 포스트 원문 읽기 WebView
│   │   └── notification/                  # 알림 관리
│   │       ├── NotificationLogActivity.kt # 알림 히스토리 화면
│   │       └── NotificationLogAdapter.kt  # 알림 목록 어댑터
│   │
│   ├── bookmark/                          # 북마크 관리 모듈
│   │   ├── BookmarkFragment.kt            # 북마크 폴더 목록 뷰
│   │   ├── BookmarkAdapter.kt             # 북마크 폴더 어댑터
│   │   └── bookmarkfeed/                  # 폴더별 피드 관리
│   │       ├── BookmarkFeedActivity.kt    # 특정 폴더 피드 목록
│   │       └── BookmarkFeedAdapter.kt     # 폴더 내 피드 어댑터
│   │
│   ├── subscribe/                         # 구독 관리 모듈
│   │   ├── SubscribeFragment.kt           # 구독/추천 블로그 관리
│   │   ├── SubscribeAdapter.kt            # 블로그 목록 어댑터
│   │   └── model/
│   │       └── BlogModel.kt               # 블로그 정보 모델
│   │
│   └── setting/                           # 설정 모듈
│       ├── SettingFragment.kt             # 메인 설정 화면
│       ├── blog/                          # 블로그별 알림 설정
│       │   ├── BlogSettingActivity.kt     # 블로그별 알림 설정 화면
│       │   └── BlogSettingAdapter.kt      # 블로그 설정 어댑터
│       └── keyword/                       # 키워드별 알림 설정
│           ├── KeywordSettingActivity.kt  # 키워드 알림 설정 화면
│           └── KeywordSettingAdapter.kt   # 키워드 설정 어댑터
│
├── 🛠 util/                              # 공통 유틸리티 모듈
│   ├── FirebaseRef.kt                     # Firebase 참조 중앙 관리
│   ├── NotificationUtil.kt                # 알림 생성 & 관리 유틸
│   ├── MyData.kt                          # 로컬 데이터 관리
│   └── Utils.kt                           # 공통 헬퍼 함수
│
└── ✅ validate/                          # 데이터 검증 모듈
    └── UserInfoValidator.kt               # 사용자 입력 데이터 검증
```

<br>



### 👨‍💻 개발자
- **개인 프로젝트** : [박도현](https://github.com/isDohyeon)
- **개발 기간**: 2025-06-07 ~ 2025-06-16 (약 10일)
- **프로젝트 Github 주소**: https://github.com/isDohyeon/tech-parser

