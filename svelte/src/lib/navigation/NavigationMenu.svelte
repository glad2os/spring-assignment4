<script>
    import {Link} from 'svelte-routing';
</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" id="my-app" href="#">MyApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <Link class="nav-link" to="/">Home</Link>
            </li>
            <li class="nav-item">
                <Link class="nav-link" to="/members">Members</Link>
            </li>
            <li class="nav-item">
                <Link class="nav-link" to="/publishers">Publishers</Link>
            </li>
            <li class="nav-item">
                <Link class="nav-link" to="/books">Books</Link>
            </li>
        </ul>
    </div>
</nav>


<style lang="scss">
  #my-app {
    margin-left: 15px;
  }
</style>