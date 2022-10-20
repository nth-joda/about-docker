Front-end app of Speech2Text
Files structure:

```
src
|__ assets
|  |__ images
|  |__ styles (global styles)
|
|__ components (shared components)
|
|__ features
    |__ Photo (Feature named "Photo")
    |   |__ components
    |   |  |__ PhotoList
    |   |  |__ PhotoCard
    |   |  |__ PhotoForm
    |   |
    |   |__ pages
    |   |  |__ MainPage
    |   |  |__ AddEditPage
    |   |__ photoSlice.js
    |   |__ index.js
    |
    |__ Video (Feature named "Video")
        |__ components
        |  |__ VA
        |  |__ VB
        |  |__ VC
        |
        |__ pages
        |  |__ VP_main
        |  |__ VP_edit
        |__ photoSlice.js
        |__ index.js
        ....
```

## Routing:

- Sử dụng kĩ thuật lazy load components.
- Load theo features.

```js
// App.js
function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/photos" component={Photo} />
        <Route path="/videos" component={Photo} />
        <Route path="/user" component={User} />
        <Route component={NotFound} />
      </Switch>
    </BrowserRouter>
  );
}
```
