<script lang="ts">
    import {Router, Route} from "svelte-routing";
    import Home from "@routes/Home.svelte";
    import NotFound from "@routes/NotFound.svelte";
    import Members from "@routes/Members.svelte";
    import Publishers from "@routes/Publishers.svelte";
    import Books from "@routes/Books.svelte";
    import NavigationMenu from "@lib/navigation/NavigationMenu.svelte";
</script>

<Router>
    <div class="page">
        <div class="navigation">
            <NavigationMenu></NavigationMenu>
        </div>

        <Route path="/" component={Home}/>
        <Route path="/members" component={Members}/>
        <Route path="/publishers" component={Publishers}/>
        <Route path="/books" component={Books}/>
        <Route path="/*" let:params>
            <NotFound/>
        </Route>
    </div>
</Router>


<style lang="scss">
  .navigation {
    margin-bottom: 25px;
  }

  .page {
    margin: 25px;
  }

  .scrollable-box {
    max-height: 300px;
    overflow-y: scroll;
    border: 1px solid #ccc;
    padding: 10px;
  }
</style>