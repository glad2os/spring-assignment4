<script>
    import {onMount} from 'svelte';
    import {writable} from "svelte/store";

    let currentTab = 'getAll';

    let allMembers = [];
    let memberId = '';
    let newMemberData = {
        name: '',
        address: '',
        membType: 'Standard',
        membDate: '',
        expiryDate: ''
    };
    const message = writable('');

    async function getAllMembers() {
        try {
            const response = await fetch('http://localhost:8080/members');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const members = await response.json();
            allMembers = members;
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function getMemberById() {
        if (!memberId) {
            message.set('Please enter a member ID');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/members/${memberId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const memberData = await response.json();

            if (memberData) {
                allMembers = [memberData];
            } else {
                allMembers = [];
                message.set('No member found with the given ID');
            }
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function deleteMemberById() {
        if (!memberId) {
            message.set('Please enter a member ID to delete');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/members/${memberId}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const currentTime = new Date().toLocaleTimeString();
            message.set(`${currentTime}: Member with ID ${memberId} deleted successfully`);

            await getAllMembers();
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    async function createMember() {
        try {
            const response = await fetch('http://localhost:8080/members', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newMemberData)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const currentTime = new Date().toLocaleTimeString();
            message.set(`${currentTime}: Member created successfully, Status: ${response.status}`);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            message.set(`Error: ${error.message}`);
        }
    }

    onMount(() => {
        getAllMembers();
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

<div class="tab-content p-3">
    {#if currentTab === 'getAll'}
        <button class="btn btn-primary mb-2" on:click={getAllMembers}>Fetch All Members</button>
        <div class="scrollable-box">
            {#each allMembers as member}
                <p>
                    ID: <strong>{member.id}</strong><br>
                    Name: <strong>{member.name}</strong><br>
                    Address: <strong>{member.address}</strong><br>
                    Membership Type: <strong>{member.membType}</strong><br>
                    Membership Date: <strong>{new Date(member.membDate).toLocaleDateString()}</strong><br>
                    Expiry Date: <strong>{new Date(member.expiryDate).toLocaleDateString()}</strong>
                </p>
            {/each}
        </div>
    {/if}

    {#if currentTab === 'getById'}
        <input type="text" class="form-control mb-2" bind:value={memberId} placeholder="Enter member ID"/>
        <button class="btn btn-primary" on:click={getMemberById}>Fetch Member</button>
        <div class="scrollable-box">
            {#each allMembers as member}
                <p>
                    ID: <strong>{member.id}</strong><br>
                    Name: <strong>{member.name}</strong><br>
                    Address: <strong>{member.address}</strong><br>
                    Membership Type: <strong>{member.membType}</strong><br>
                    Membership Date: <strong>{new Date(member.membDate).toLocaleDateString()}</strong><br>
                    Expiry Date: <strong>{new Date(member.expiryDate).toLocaleDateString()}</strong>
                </p>
            {/each}
        </div>
    {/if}

    {#if currentTab === 'deleteById'}
        <input type="text" class="form-control mb-2" bind:value={memberId} placeholder="Enter member ID"/>
        <button class="btn btn-danger" on:click={deleteMemberById}>Delete Member</button>
        <p>{$message}</p>
    {/if}

    {#if currentTab === 'create'}
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control mb-2" id="name" bind:value={newMemberData.name}
                   placeholder="Member Name"/>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control mb-2" id="address" bind:value={newMemberData.address}
                   placeholder="Member Address"/>
        </div>
        <div class="form-group">
            <label for="membType">Membership Type</label>
            <input type="text" class="form-control mb-2" id="membType" bind:value={newMemberData.membType}
                   placeholder="Membership Type"/>
        </div>
        <div class="form-group">
            <label for="membDate">Membership Date</label>
            <input type="date" class="form-control mb-2" id="membDate" bind:value={newMemberData.membDate}/>
        </div>
        <div class="form-group">
            <label for="expiryDate">Expiry Date</label>
            <input type="date" class="form-control mb-2" id="expiryDate" bind:value={newMemberData.expiryDate}/>
        </div>
        <button class="btn btn-success" on:click={createMember}>Create Member</button>
        <p>{$message}</p>
    {/if}
</div>

