function renderPostImages() {
    const imageGroups = document.querySelectorAll('.post-image-group');
    
    imageGroups.forEach(group => {
        const src = group.getAttribute('data-src');
        const caption = group.getAttribute('data-caption');
        
        if (!src) return;
        
        const html = `
            <img src="${src}" class="post-image" alt="">
            ${caption ? `<p class="post-caption">${caption}</p>` : ''}
        `;
        
        group.innerHTML = html;
    });
}

// ページ読み込み後に実行
document.addEventListener('DOMContentLoaded', renderPostImages);
