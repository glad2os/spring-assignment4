<script>
    import {onMount} from 'svelte';
    import {writable} from "svelte/store";

    let currentTab = 'getAll';

    let allPublishers = [];
    let publisherId = '';
    let newPublisherData = {
        name: '',
        address: ''
    };
    const message = writable('');

    async function getAllPublishers() {
        try {
            const response = await fetch('http://localhost:8080/publishers');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            allPublishers = await response.json();
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function getPublisherById() {
        if (!publisherId) {
            message.set('Please enter a publisher ID');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/publishers/${publisherId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            allPublishers = [await response.json()];
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function deletePublisherById() {
        if (!publisherId) {
            message.set('Please enter a publisher ID to delete');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/publishers/${publisherId}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            message.set(`Publisher with ID ${publisherId} deleted successfully`);
            await getAllPublishers();
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function createPublisher() {
        try {
            const response = await fetch('http://localhost:8080/publishers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newPublisherData)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            message.set(`Publisher created successfully`);
            await getAllPublishers();
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    onMount(() => {
        getAllPublishers();
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
            a New Publisher</a>
    </li>
</ul>

<div class="tab-content p-3">
    {#if currentTab === 'getAll'}
        <button class="btn btn-primary mb-2" on:click={getAllPublishers}>Fetch All Publishers</button>
        <div class="scrollable-box">
            {#each allPublishers as publisher}
                <p>
                    ID: <strong>{publisher.id}</strong><br>
                    Name: <strong>{publisher.name}</strong><br>
                    Address: <strong>{publisher.address}</strong>
                </p>
            {/each}
        </div>
    {/if}

    {#if currentTab === 'getById'}
        <input type="text" class="form-control mb-2" bind:value={publisherId} placeholder="Enter publisher ID"/>
        <button class="btn btn-primary" on:click={getPublisherById}>Fetch Publisher</button>
        <div class="scrollable-box">
            {#each allPublishers as publisher}
                <p>
                    ID: <strong>{publisher.id}</strong><br>
                    Name: <strong>{publisher.name}</strong><br>
                    Address: <strong>{publisher.address}</strong>
                </p>
            {/each}
        </div>
    {/if}

    {#if currentTab === 'deleteById'}
        <input type="text" class="form-control mb-2" bind:value={publisherId} placeholder="Enter publisher ID"/>
        <button class="btn btn-danger" on:click={deletePublisherById}>Delete Publisher</button>
        <p>{$message}</p>
    {/if}

    {#if currentTab === 'create'}
        <div class="form-group">
            <label for="name">Publisher Name</label>
            <input type="text" class="form-control mb-2" id="name" bind:value={newPublisherData.name}
                   placeholder="Publisher Name"/>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control mb-2" id="address" bind:value={newPublisherData.address}
                   placeholder="Publisher Address"/>
        </div>
        <button class="btn btn-success" on:click={createPublisher}>Create Publisher</button>
        <p>{$message}</p>
    {/if}
</div>

