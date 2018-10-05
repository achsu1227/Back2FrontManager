[![](https://jitpack.io/v/achsu1227/Back2FrontManager.svg)](https://jitpack.io/#achsu1227/Back2FrontManager)

# Back2FrontManager
android detected Background to Front

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
dependencies {
	        implementation 'com.github.achsu1227:Back2FrontManager:Tag'
}
```

how to use:
```
public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
	// user Back2FrontManager to add Kit event.
        Back2FrontManager.with(new Back2FrontManager.Builder(this).addKit(new Kit() {
            @Override
            public void execute(Activity activity) {
                Log.e("hello", "hello");
            }
        }));
    }
}
```
