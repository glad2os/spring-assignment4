<script>
    import {onMount} from 'svelte';
    import {writable} from "svelte/store";

    let currentTab = 'getAll';

    let allBooks = [];
    let bookId = '';
    let newBookData = {title: '', author: '', price: 0, available: true};
    const message = writable('');

    async function getAllBooks() {
        try {
            const response = await fetch('http://localhost:8080/books');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const books = await response.json();
            allBooks = books;
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function getBookById() {
        if (!bookId) {
            message.set('Please enter a book ID');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/books/${bookId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const bookData = await response.json();

            if (bookData) {
                allBooks = [bookData];
            } else {
                allBooks = [];
                message.set('No book found with the given ID');
            }
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function deleteBookById() {
        if (!bookId) {
            message.set('Please enter a book ID to delete');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/books/${bookId}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const currentTime = new Date().toLocaleTimeString();
            message.set(`${currentTime}: Book with ID ${bookId} deleted successfully`);

            await getAllBooks();
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }


    async function createBook() {
        try {
            const response = await fetch('http://localhost:8080/books', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newBookData)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const currentTime = new Date().toLocaleTimeString();
            message.set(`${currentTime}: Status ${response.status}`);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    onMount(() => {
        getAllBooks();
    });
</script>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link {currentTab === 'getAll' ? 'active' : ''}" href="#" on:click={() => currentTab = 'getAll'}>Get
            All</a>
    </li>
    <li class="nav-item">
        <a class="nav-link {currentTab === 'getById' ? 'active' : ''}" href="#" on:click={() => currentTab = 'getById'}>Get
            by ID</a>
    </li>
    <li class="nav-item">
        <a class="nav-link {currentTab === 'deleteById' ? 'active' : ''}" href="#"
           on:click={() => currentTab = 'deleteById'}>Delete by ID</a>
    </li>
    <li class="nav-item">
        <a class="nav-link {currentTab === 'create' ? 'active' : ''}" href="#" on:click={() => currentTab = 'create'}>Create
            a New Book</a>
    </li>
</ul>

<!-- Tab Content -->
<div class="tab-content p-3">
    {#if currentTab === 'getAll'}
        <button class="btn btn-primary mb-2" on:click={getAllBooks}>Fetch All Books</button>
        <div class="scrollable-box">
            {#each allBooks as book}
                <p>ID: <strong>{book.id}</strong> <strong>{book.title}</strong> by <strong>{book.author}</strong> -
                    <strong>${book.price}</strong> - Available: <strong>{book.available ? 'Yes' : 'No'}</strong></p>
            {/each}
        </div>
    {/if}


    {#if currentTab === 'getById'}
        <input type="text" class="form-control mb-2" bind:value={bookId} placeholder="Enter book ID"/>
        <button class="btn btn-primary" on:click={getBookById}>Fetch Book</button>
        <div class="scrollable-box">
            {#each allBooks as book}
                <p>{book.title} by {book.author} - ${book.price} - Available: {book.available ? 'Yes' : 'No'}</p>
            {/each}
        </div>
    {/if}


    {#if currentTab === 'deleteById'}
        <input type="text" class="form-control mb-2" bind:value={bookId} placeholder="Enter book ID"/>
        <button class="btn btn-danger" on:click={deleteBookById}>Delete Book</button>
        <p>{$message}</p>
    {/if}


    {#if currentTab === 'create'}
        <div class="form-group">
            <label for="title">Book Title</label>
            <input type="text" class="form-control mb-2" id="title" bind:value={newBookData.title}
                   placeholder="Book Title"/>
        </div>
        <div class="form-group">
            <label for="author">Author Name</label>
            <input type="text" class="form-control mb-2" id="author" bind:value={newBookData.author}
                   placeholder="Author Name"/>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" class="form-control mb-2" id="price" bind:value={newBookData.price} placeholder="19.99"
                   step="0.01"/>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="available" bind:checked={newBookData.available}/>
            <label class="form-check-label" for="available">Available</label>
        </div>
        <button class="btn btn-success" on:click={createBook}>Create Book</button>
        <p>{$message}</p>
    {/if}
</div>