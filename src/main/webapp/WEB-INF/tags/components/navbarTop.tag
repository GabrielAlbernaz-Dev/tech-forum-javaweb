<%@ tag description="Navbar Top Component" pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a href="/" class="navbar-brand text-decoration-none text-dark">
            <h1 class="fs-2 text-light">TechForum</h1>
        </a>

        <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-layout-text-sidebar-reverse"></i> Categories
                    </a>
                    <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/">Programming</a></li>
                        <li><a class="dropdown-item" href="/">Hardware</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/"><i class="bi bi-star-fill text-warning"></i> Top</a>
                </li>
            </ul>
            <div class="btn-group flex-md-row flex-column" role="group">
                <button type="button" class="btn text-light dropdown-toggle px-0 px-md-2" data-bs-toggle="dropdown" aria-expanded="false">
                    UsernameRandomXX
                </button>
                <ul class="dropdown-menu bg-dark">
                    <li><a class="dropdown-item bg-dark text-light" href="/"><i class="bi bi-person"></i>Profile Page</a></li>
                    <li><a class="dropdown-item" href="/"><i class="bi bi-sticky"></i>My Posts</a></li>
                    <li><a class="dropdown-item" href="/"><i class="bi bi-bell"></i>My Notifications</a></li>
                    <li><a class="dropdown-item" href="/"><i class="bi bi-hand-thumbs-up"></i>Likes Received</a></li>
                    <li><a class="dropdown-item" href="/"><i class="bi bi-box-arrow-right"></i>Logout</a></li>
                </ul>
                <button class="btn text-light px-0 px-md-2 d-flex d-md-inline-block">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </div>
    </div>
</nav>